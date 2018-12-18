package com.hnen.transdata.log;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.hnen.transdata.websocket.JobtaskWebSocket;

import java.io.IOException;


public class WebsocketAppender extends AppenderBase<LoggingEvent> {


//    @Autowired
//    private JobtaskWebSocket webSocket;
    @Override
    protected void append(LoggingEvent event) {
//
//             System.out.println("自定义appender:thread:[ "+Thread.currentThread()+"]");
//          System.out.println("自定义appender:getOnlineCount:[ "+MyWebSocket.getOnlineCount()+"]");
//          System.out.println("自定义appender:webSocketSet: "+MyWebSocket.webSocketSet);
//          System.out.println("自定义appender:webSocket: "+webSocket);
//          System.out.println("+1+"+event.getLevel()+event.getMessage()+event.getThreadName());
//
//
//        System.out.println("+2+"+event.getMessage());
        try {
            JobtaskWebSocket.sendsMessage(event.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("+3+"+event.getMessage());

    }
}
