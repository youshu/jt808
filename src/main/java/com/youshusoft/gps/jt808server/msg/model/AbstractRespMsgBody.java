package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.wrapper.ValueWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: 悠树
 * @create: 2020-06-21 21:34
 **/
public abstract class AbstractRespMsgBody implements RespMsgBody{
    public byte[] toBytes(ValueWrapper... msgs) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (ValueWrapper msg : msgs) {
            outputStream.write(msg.getBytes());
        }
        return outputStream.toByteArray();
    }
}
