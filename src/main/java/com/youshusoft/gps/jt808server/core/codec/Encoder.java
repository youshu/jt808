package com.youshusoft.gps.jt808server.core.codec;

import com.google.common.collect.Lists;
import com.youshusoft.gps.jt808server.core.JtConstant;
import com.youshusoft.gps.jt808server.core.wrapper.BcdWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import com.youshusoft.gps.jt808server.msg.model.RespMsg;
import com.youshusoft.gps.jt808server.msg.model.RespMsgBody;
import com.youshusoft.gps.jt808server.util.ProtocolUtils;
import io.github.hylexus.oaks.utils.Bytes;
import io.github.hylexus.oaks.utils.IntBitOps;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author hylexus
 * createdAt 2019/2/5
 **/
@Slf4j
public class Encoder {

    @Getter
    private final BytesEncoder bytesEncoder;


    public Encoder(BytesEncoder bytesEncoder) {
        this.bytesEncoder = bytesEncoder;
    }


    public int generateMsgBodyProps(int msgBodySize, int encryptionType, boolean isSubPackage, int reversedLastBit) {

        if (msgBodySize >= 1024) {
            log.warn("The max value of msgBodySize is 1024, but {} .", msgBodySize);
        }

        // [ 0-9 ] 0000,0011,1111,1111(3FF)(消息体长度)
        int props = (msgBodySize & 0x3FF)
                // [10-12] 0001,1100,0000,0000(1C00)(加密类型)
                | ((encryptionType << 10) & 0x1C00)
                // [ 13_ ] 0010,0000,0000,0000(2000)(是否有子包)
                | (((isSubPackage ? 1 : 0) << 13) & 0x2000)
                // [14-15] 1100,0000,0000,0000(C000)(保留位)
                | ((reversedLastBit << 14) & 0xC000);
        return props & 0xFFFF;
    }

    private byte[] generateMsgHeader4Resp(WordWrapper msgId, int bodyProps, BcdWrapper terminalId, WordWrapper flowId) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 1. 消息ID word(16)
            baos.write(msgId.getBytes());
            // 2. 消息体属性 word(16)
            baos.write(IntBitOps.intTo2Bytes(bodyProps));
            // 3. 终端手机号 bcd[6]
            baos.write(terminalId.getBytes());
            // 4. 消息流水号 word(16),按发送顺序从 0 开始循环累加
            baos.write(flowId.getBytes());
            // 消息包封装项 此处不予考虑
            return baos.toByteArray();
        }
    }

    private byte[] doEncode(byte[] headerAndBody, byte checkSum, boolean escape) throws IOException {
        byte[] noEscapedBytes = Bytes.concatAll(Lists.newArrayList(//
                new byte[]{JtConstant.PACKAGE_DELIMITER}, // 0x7e
                headerAndBody, // 消息头+ 消息体
                new byte[]{checkSum},// 校验码
                new byte[]{JtConstant.PACKAGE_DELIMITER}// 0x7e
        ));
        if (escape) {
            return this.bytesEncoder.doEscapeForSend(noEscapedBytes, 1, noEscapedBytes.length - 2);
            // return ProtocolUtils.doEscape4SendJt808Msg(noEscapedBytes, 1, noEscapedBytes.length - 2);
        }
        return noEscapedBytes;
    }

    public byte[] encodeRespMsg(RespMsg respMsg) throws IOException {
        return encodeRespMsg(respMsg.getBody(),WordWrapper.valueOf(respMsg.getFlowId()),BcdWrapper.valueOf(respMsg.getTerminalId()));
    }
    public byte[] encodeRespMsg(RespMsgBody bodySupport, WordWrapper flowId, BcdWrapper terminalId) throws IOException {
        byte[] body = bodySupport.toBytes();
        int bodyProps = this.generateMsgBodyProps(body.length, 0b000, false, 0);
        byte[] header = this.generateMsgHeader4Resp(WordWrapper.valueOf(bodySupport.replyMsgType()), bodyProps, terminalId, flowId);
        byte[] headerAndBody = Bytes.concatAll(Lists.newArrayList(header, body));
        byte checkSum = ProtocolUtils.calculateCheckSum4Jt808(headerAndBody, 0, headerAndBody.length);
        return doEncode(headerAndBody, checkSum, true);
    }
}
