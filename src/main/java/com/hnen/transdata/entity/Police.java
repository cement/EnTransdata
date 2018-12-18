package com.hnen.transdata.entity;

import lombok.Data;


@Data
public class Police {

    private String _id;
    private String policeNo;//民警警号
    private String policeName;//民警姓名
    private String paperId;//证件号码
    private String tel;//联系电话
    private String openId;//openId
    private String passWord;//密码

    public Police() {
    }

    public Police(String policeNo, String policeName) {
        this.policeNo = policeNo;
        this.policeName = policeName;
    }
}
