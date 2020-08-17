
package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.wrapper.BcdWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.DwordWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hylexus
 * Created At 2020-01-29 12:03 下午
 */
@Data
public class LocationRequestMsgBody implements RequestMsgBody {


    // 报警标志
    private AlarmStatus alarmFlag;

    // 状态
    private LocationStatus status;

    // 纬度
    private DwordWrapper lat;

    // 经度
    private DwordWrapper lng;

    // 高度
    private WordWrapper height;

    // 速度
    private WordWrapper speed;

    // 方向
    private WordWrapper direction;

    private BcdWrapper time;
    private List<LbsModel> lbsList;
    

    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
        this.setAlarmFlag(new AlarmStatus(msgBuf.readDword()));
        this.setStatus(new LocationStatus(msgBuf.readDword()));
        this.setLat(msgBuf.readDword());
        this.setLng(msgBuf.readDword());
        this.setHeight(msgBuf.readWord());
        this.setSpeed(msgBuf.readWord());
        this.setDirection(msgBuf.readWord());
        this.setTime(msgBuf.readBcd(6));
        while (msgBuf.readableBytes() > 0){
            //拓展信息
            int extraId = msgBuf.readByte().toInt();
            int extraLength = msgBuf.readByte().toInt();
            MsgBuf extraMsgBuf = msgBuf.readMsgBuf(extraLength);
            switch (extraId){
                case 0x53:
                    int number = extraMsgBuf.readByte().toInt();
                    List<LbsModel> lbsModelList = new ArrayList<>();
                    for (int i = 0; i < number; i++) {
                        LbsModel lbsModel = new LbsModel(extraMsgBuf.readMsgBuf(8));
                        lbsModelList.add(lbsModel);
                    }
                    this.setLbsList(lbsModelList);
                    break;
            }
        }
    }

    @Data
    public static class AlarmStatus {
        private boolean sos; //SOS
        private boolean speeding; //超速
        private boolean undervoltage; //欠压
        private boolean powerDown; //掉电
        private boolean collide; //碰撞
        private boolean rapidAcceleration; //急加速
        private boolean flip; //翻转
        private boolean abruptDeceleration; //急减速
        private boolean sharpTurn; //急转弯

        public AlarmStatus(DwordWrapper dwordWrapper) {
            sos = dwordWrapper.readBooleanForBit();
            speeding = dwordWrapper.readBooleanForBit();
            undervoltage = dwordWrapper.getBooleanForBit(7);
            powerDown = dwordWrapper.getBooleanForBit(8);
            collide = dwordWrapper.getBooleanForBit(16);
            rapidAcceleration = dwordWrapper.getBooleanForBit(17);
            flip = dwordWrapper.getBooleanForBit(29);
            abruptDeceleration = dwordWrapper.getBooleanForBit(30);
            sharpTurn = dwordWrapper.getBooleanForBit(31);
        }
    }
    @Data
    public static class LocationStatus {
        private boolean acc; // 是否acc开
        private boolean location; //是否定位
        private boolean latType; //是否南纬
        private boolean lngType; //是否西经
        private boolean oil; //是否断开油路
        public LocationStatus(DwordWrapper dwordWrapper) {
            acc = dwordWrapper.readBooleanForBit();
            location = dwordWrapper.readBooleanForBit();
            latType = dwordWrapper.readBooleanForBit();
            lngType = dwordWrapper.readBooleanForBit();
            oil = dwordWrapper.getBooleanForBit(10);
            
        }
    }

    @Data
    public static class LbsModel {
        public LbsModel(MsgBuf msgBuf) {
            mcc = msgBuf.readWord().toInt() + "";
            mnc = msgBuf.readByte().toInt() + "";
            lac = msgBuf.readWord().toInt();
            cellid = msgBuf.readWord().toInt() + "";
            signal = msgBuf.readByte().toInt();
        }

        private String mcc;
        private String mnc;
        private Integer lac;
        private String cellid;
        private Integer signal;

    }
}
