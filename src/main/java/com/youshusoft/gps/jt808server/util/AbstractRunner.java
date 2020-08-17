package com.youshusoft.gps.jt808server.util;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author hylexus
 * createdAt 2019/2/1
 **/
@Slf4j
public abstract class AbstractRunner {

    protected volatile boolean isRunning = false;

    public abstract void doProcessBlocked() throws Exception;

    public abstract void onDestroy() throws Exception;

    @PostConstruct
    public synchronized void doStart() {
        if (this.isRunning) {
            throw new IllegalStateException(this.getName() + " is already started .");
        }
        this.isRunning = true;

        new Thread(() -> {
            try {
                doProcessBlocked();
            } catch (Exception e) {
                log.error("", e);
            }
        }, this.getName()).start();

        log.info("{} started successfully ", getName());
    }

    @PreDestroy
    public synchronized void doStop() {
        if (!this.isRunning) {
            throw new IllegalStateException(this.getName() + " is not yet started .");
        }
        this.isRunning = false;

        try {
            onDestroy();
        } catch (Exception e) {
            log.error("", e);
        }

        log.info("{} stopped successfully ", getName());
    }

    public String getName() {
        return "[RUNNER : " + this.getClass().getSimpleName() + "]";
    }

}
