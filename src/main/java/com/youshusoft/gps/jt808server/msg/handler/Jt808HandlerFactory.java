package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Jt808HandlerFactory {
    private static final Map<Integer, AbstractJt808RequestMsgHandler> IN_PROTOCOL_HANDLER_MAP = new HashMap<Integer, AbstractJt808RequestMsgHandler>();
    private static final String SCAN_BASE_PACKAGE = Jt808HandlerFactory.class.getPackage().getName();

    public Jt808HandlerFactory() {
        init();
    }

    public void init(){
        List<Class<?>> classList = ClassUtil.getClasses(SCAN_BASE_PACKAGE);
        for (Class c : classList) {
            if(AbstractJt808RequestMsgHandler.class.isAssignableFrom(c)){
                if(c == AbstractJt808RequestMsgHandler.class){
                    continue;
                }
                try {
                    AbstractJt808RequestMsgHandler handler = (AbstractJt808RequestMsgHandler)c.newInstance();
                    IN_PROTOCOL_HANDLER_MAP.put(handler.getMsgId(), handler);
                    log.info("init handler " + handler.getMsgId());
                }catch (Exception e){
                    log.error("serverHandler init err",e);
                }
            }
            

        }
    }
    

    public AbstractJt808RequestMsgHandler getInProtocolHandler(Integer msgId) {
        
        return IN_PROTOCOL_HANDLER_MAP.get(msgId);
    }

}
