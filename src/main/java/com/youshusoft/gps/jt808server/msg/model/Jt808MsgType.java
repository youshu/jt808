package com.youshusoft.gps.jt808server.msg.model;


import com.youshusoft.gps.jt808server.util.HexStringUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2019/1/24
 **/
@Getter
public enum Jt808MsgType implements MsgType {

    CLIENT_LOG_OUT(0x0003, "终端注销"),
    CLIENT_HEART_BEAT(0x0002, "终端心跳"),

    CLIENT_COMMON_REPLY(0x0001, "终端通用应答"),
    SERVER_COMMON_REPLY(0x8001, "平台通用应答"),

    CLIENT_REGISTER(0x0100, "终端注册"),
    CLIENT_REGISTER_REPLY(0x8100, "平台注册应答"),
    CLIENT_AUTH(0x0102, "终端鉴权"),
    CLIENT_LOCATION_INFO_UPLOAD(0x0200, "位置上报"),
    CLIENT_BATCH_LOCATION_INFO_UPLOAD(0x0704, "位置批量上报"),
    ICCOD_UPLOAD(0x0120, "ICCID上报"),
    ICCOD_UPLOAD_REPLY(0x8120, "ICCID上报应答"),
    SETTINGS(0x8103, "设备设置"),
    ORDER(0x8105, "设备控制"),
    QUERY(0x8201, "位置查询"),
    QUERY_REPLY(0x0201, "位置查询回复"),
    ;

    Jt808MsgType(int msgId, String desc) {
        this.msgId = msgId;
        this.desc = desc;
    }
    private int msgId;
    private String desc;
    private static final Map<Integer, Jt808MsgType> mapping = new HashMap<>(Jt808MsgType.values().length);

    static {
        for (Jt808MsgType builtinMsgType : values()) {
            mapping.put(builtinMsgType.msgId, builtinMsgType);
        }
    }

    @Override
    public Optional<MsgType> parseFromInt(int msgId) {
        return Optional.ofNullable(mapping.get(msgId));
    }

    @Override
    public String toString() {
        return "BuiltInMsgType{"
                + "msgId=" + msgId
                + "(" + HexStringUtils.int2HexString(msgId, 4, true) + "), desc='" + desc + '\''
                + '}';
    }
}
