package com.hnen.transdata.entity;

import lombok.Data;

@Data
public class Hsmdata {

    private String id;
    private String name;
    private byte[] contents;
    private String inserttime;
    private String transtime;


}
