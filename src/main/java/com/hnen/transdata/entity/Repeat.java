package com.hnen.transdata.entity;

import lombok.Data;

//民警反馈类
@Data
public class Repeat {

    private Police police; //反馈民警
    private Boolean ifTrue; //是否属实
    private String caseMemo;  //简要案情
    private String repeatResult;  //反馈结果
    private String sceneMemo; //现场描述
    private String[] photoFiles; // 上传图片文件
    private String[] vedioFiles; // 上传视频文件
    private String repeatTime; //反馈时间


    private Long repeatId;                 //@ysh 指令表唯一标识
    private String reportCode; //@ysh 举报唯一标识

    public Repeat() {
    }


}
