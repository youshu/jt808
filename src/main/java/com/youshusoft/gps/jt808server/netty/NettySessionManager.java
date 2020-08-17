package com.youshusoft.gps.jt808server.netty;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NettySessionManager {
    private final Object lock = new Object();
    private static volatile NettySessionManager instance = new NettySessionManager();

    private NettySessionManager() {
    }

    public static NettySessionManager getInstance() {
        return instance;
    }

    // <terminalId,Session>
    private Map<String, NettySession> sessionMap = new ConcurrentHashMap<>();
    // <sessionId,terminalId>
    private Map<String, String> sessionIdTerminalIdMapping = new ConcurrentHashMap<>();

    public NettySession persistenceIfNecessary(String terminalId, Channel channel) {
        Optional<NettySession> session = findByTerminalId(terminalId, true);
        if (!session.isPresent()) {
            NettySession newSession = NettySession.buildSession(channel, terminalId);
            persistence(newSession);
            return newSession;
        }else{
            NettySession oldSession = session.get();
            if(!oldSession.getChannel().id().asLongText().equals(channel.id().asLongText())){
                removeBySessionIdAndClose(oldSession.getId());
                NettySession newSession = NettySession.buildSession(channel, terminalId);
                persistence(newSession);
                return newSession;
            }
            return oldSession;
        }
        
    }

    public void persistence(NettySession session) {
        synchronized (lock) {
            this.sessionMap.put(session.getTerminalId(), session);
            sessionIdTerminalIdMapping.put(session.getId(), session.getTerminalId());
        }
    }
    public void removeByChannel(Channel channel) {
        removeBySessionIdAndClose(NettySession.generateSessionId(channel));
    }
    public void removeBySessionIdAndClose(String sessionId) {
        synchronized (lock) {
            sessionIdTerminalIdMapping.remove(sessionId);
            sessionMap.remove(sessionId);
        }
        log.info("session removed [{}] , sessionId = {}", sessionId);
    }

    public Optional<NettySession> findByTerminalId(String terminalId) {
        return findByTerminalId(terminalId, false);
    }

    public Optional<NettySession> findByTerminalId(String terminalId, boolean updateLastCommunicateTime) {
        NettySession session = sessionMap.get(terminalId);
        if (session == null) {
            return Optional.empty();
        }

        if (updateLastCommunicateTime) {
            synchronized (lock) {
                session.setLastActivityTime(System.currentTimeMillis());
            }
        }

        if (!this.checkStatus(session)) {
            return Optional.empty();
        }

        return Optional.of(session);
    }

    private boolean checkStatus(NettySession session) {
        if (!session.isActive()) {
            synchronized (lock) {
                this.sessionMap.remove(session.getId());
                sessionIdTerminalIdMapping.remove(session.getId());
                session.getChannel().close();
                log.error("Close by server, terminalId = {}", session.getTerminalId());
                return false;
            }
        }

        return true;
    }
}
