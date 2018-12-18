package com.hnen.transdata.websocket;

import com.hnen.transdata.serviceimpl.CasedataTaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint(value = "/websocket")
public class JobtaskWebSocket {
    private static final Logger logger = LoggerFactory.getLogger(CasedataTaskServiceImpl.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<JobtaskWebSocket> webSocketSet = new CopyOnWriteArraySet<JobtaskWebSocket>();
//    private static Set<JobtaskWebSocket> webSocketSet = new HashSet<JobtaskWebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("connect server success!");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (JobtaskWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    public  void sendMessage(String message) throws IOException {
        //this.session.getBasicRemote().sendText(message);
        this.session.getAsyncRemote().sendText(message);
    }
    /**
     * 群发自定义消息
     */
    public  static void sendsMessage(String message) throws IOException {
        for (JobtaskWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
                logger.info("websocket 发送消息："+message);
            } catch (IOException e) {
                logger.info("websocket 发送错误："+e.getStackTrace());
                continue;
            }
        }
    }
    /**
     * 群发自定义消息
     */
    public static void sendMessageGroup(String message) {
        for (JobtaskWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
                logger.info("websocket 发送消息："+message);
            } catch (IOException e) {
                logger.info("websocket 发送错误："+e.getStackTrace());
                continue;
            }
        }
    }
    public static synchronized int getOnlineCount() {

        return onlineCount.get();
    }
    public static synchronized void addOnlineCount() {
        onlineCount.incrementAndGet();    }
    public static synchronized void subOnlineCount() {
        onlineCount.decrementAndGet();
    }
}


