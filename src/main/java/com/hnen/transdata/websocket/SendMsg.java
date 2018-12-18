package com.hnen.transdata.websocket;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 向前端发送消息类
 */
public class SendMsg {

    private String classid="" ;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public SendMsg() {
    }

    public SendMsg(String classid) {
        this.classid = classid;
    }

    /**
     *向客户端发送消息
     * @param
     * @param content
     */
     public  void msgtosend(String subClass ,String content)  {
               Map<String,Object> paras = new HashMap<String,Object>();
               paras.put("classId" , this.classid);
               paras.put("subClass",subClass); //小类
               Date date=new Date();
               DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               content =format.format(date) + ":  " + content;
               paras.put("dt",format.format(date));
               paras.put("content", content);
//             try {
////                 MyWebSocket.sendInfo(JSON.toJSONString(paras));
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
     }



}
