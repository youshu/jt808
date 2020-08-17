package com.youshusoft.gps.jt808server.core.wrapper;

import com.youshusoft.gps.jt808server.core.exception.ValueWrapperEncodingException;
import io.github.hylexus.oaks.utils.BcdOps;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 8421 码，n 字节
 * @author: 悠树
 * @create: 2020-06-20 22:37
 **/
public class BcdWrapper extends ValueWrapper {
    public BcdWrapper(byte[] bytes) {
        super(bytes);
    }
    public static BcdWrapper valueOf(String bcdString){
        return new BcdWrapper(BcdOps.string2Bcd(bcdString));
    }
    @Override
    public String toString() {
        return BcdOps.bcd2String(bytes);
    }
    public Date toDate(){
        String str = toString();
        try {
            return DateUtils.parseDate(str,"yyMMddHHmmss");
        } catch (ParseException e) {
            throw new ValueWrapperEncodingException(e);
        }
    }
}
