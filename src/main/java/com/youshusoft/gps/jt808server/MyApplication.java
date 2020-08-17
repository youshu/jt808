package com.youshusoft.gps.jt808server;

import com.youshusoft.gps.jt808server.netty.Jt808NettyTcpServer;
import com.youshusoft.gps.jt808server.util.AbstractRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@Slf4j
public class MyApplication{
    public static ApplicationContext applicationContext;
    public static void main(String[] args) {
         MyApplication.applicationContext =  new SpringApplicationBuilder(MyApplication.class).web(WebApplicationType.NONE).run(args);
        Jt808NettyTcpServer jt808NettyTcpServer = new Jt808NettyTcpServer(6789);
        try {
            jt808NettyTcpServer.start();
        } catch (InterruptedException e) {
            log.error("InterruptedException err",e);
            System.exit(0);
        }
    }

}
