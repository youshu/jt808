# JT808协议Java解析服务端

参考Netty的ByteBuf设计,将数据封装到MsgBuf中,提供顺序或随机方法读取JT808数据类型

	//顺序读取方法
    WordWrapper readWord();
    DwordWrapper readDword();
    ByteWrapper readByte();
    ByteArrayWrapper readByteArray(int length);
    MsgBuf readMsgBuf(int length);
    BcdWrapper readBcd(int length);
    StringWrapper readString(int length);
    
    //随机读取方法
    WordWrapper getWord(int index);
    DwordWrapper getDword(int index);
    ByteWrapper getByte(int index);
    ByteArrayWrapper getByteArray(int index, int length);
    BcdWrapper getBcd(int index, int length);
    StringWrapper getString(int index, int length);


## 数据解析示例(位置数据解析,LocationRequestMsgBody类)

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

由协议对应的MsgHandler完成数据解码（decode）和业务处理(handle)

项目中有很多地方直接使用 https://hylexus.github.io/jt-framework/ 项目中的代码，代码中也保留了作者信息，非常感谢jt-framework项目作者的无私奉献。

微信号youshusoft，欢迎技术交流。

### End