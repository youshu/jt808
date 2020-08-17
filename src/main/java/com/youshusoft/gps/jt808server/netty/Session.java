package com.youshusoft.gps.jt808server.netty;

public interface Session<T> {
    void out(T t);
    boolean isActive();
}
