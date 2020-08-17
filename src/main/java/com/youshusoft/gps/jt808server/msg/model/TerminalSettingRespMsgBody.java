package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.DwordWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.ValueWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 悠树
 * @create: 2020-07-08 20:16
 **/
@Slf4j
public class TerminalSettingRespMsgBody extends AbstractRespMsgBody {
    private List<SettingItem> settingItemList = new ArrayList<>();
    @Override
    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(settingItemList.size());
        for (SettingItem item:settingItemList) {
            outputStream.write(item.toBytes());
        }
        return outputStream.toByteArray();
    }

    public TerminalSettingRespMsgBody(SettingItem item) {
        addSettingItem(item);
    }

    public void addSettingItem(SettingItem item){
        settingItemList.add(item);
    }
    @Override
    public Integer replyMsgType() {
        return Jt808MsgType.SETTINGS.getMsgId();
    }
    
    public static class SettingItem {
        public static final  DwordWrapper REPORTING_INTERVAL = DwordWrapper.valueOf(0x0029);
        private DwordWrapper settingId;

        private ByteWrapper contentLength;

        private ValueWrapper content;
        
        public SettingItem(DwordWrapper settingId, ValueWrapper content) {
            this.settingId = settingId;
            this.content = content;
            this.contentLength = ByteWrapper.valueOf(content.getBytes().length);
        }
        @SneakyThrows
        public byte[] toBytes()   {
            return ValueWrapper.concat(settingId, contentLength,content);
        }
    }
}
