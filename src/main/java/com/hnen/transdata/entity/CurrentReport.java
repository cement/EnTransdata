package com.hnen.transdata.entity;

import lombok.Data;

import java.util.Random;

//单次举报类

@Data
public class CurrentReport {

    private String id;
//    private String mongoId;
    private String markCode ; //识别码 （6位字母数字生成）
    private Report report; // 举报类
    private Repeat repeat; //反馈类
    private Boolean reportTransed ; // 举报是否已传内网
    private Boolean repeatTransed;  // 反馈是否已传内网
    private Boolean ifProcessed;       //是否已处理

    public String makeCode(int length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }



}

