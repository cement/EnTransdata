package com.hnen.transdata.entity;

import lombok.Data;

/**
 * 实名制举报者信息
 */

@Data
public class Transer {

    private String _id;
    private String openId;
    private String name;
    private String paperId;
    private String tel;

    public Transer() {
    }

    public Transer(String openId, String name, String paperId, String tel) {
        this.openId = openId;
        this.name = name;
        this.paperId = paperId;
        this.tel = tel;
    }

}
