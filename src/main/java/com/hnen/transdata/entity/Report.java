package com.hnen.transdata.entity;

import lombok.Data;

// 举报类
@Data
public class Report {
    private boolean ifYellowing ;// 是否涉黄
    private boolean ifBet; //是否涉赌
    private String placeAdd;//场所地址
    private String  placeName ; //场所名称
    private String inPlace; //发生场所
    private Double[] location ; //经纬度坐标
    private String memo ; //  举报描述
    private String[] photoFiles; // 图片文件名
    private String[] vedioFiles ; // 视频文件名
    private String[] audioFiles ; // 音频文件名
    private Transer transer ;    // 实名内容
    private String reportTime; //举报时间
    private String province;    //所属省
    private String city;         //所属市
    private String district;      //所属区



    private Integer reportKind;   //@ysh所属区ID
    private String cityId;   //@ysh所属区ID
    private String districtId; //@ysh所属区ID
    private String reportCode;//@ysh生成的报告唯一编码









    public Report() {
    }

}
