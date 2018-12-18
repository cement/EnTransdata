package com.hnen.transdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/importdata", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
public class CaseDataController {



    @RequestMapping("/start")
    public String  startImportData(){
//        CaseDataTask.startImportData();
        return "开始导入数据";
    }

    @RequestMapping("/stop")
    public String  stopImportData(){
//        CaseDataTask.stopImportData();
        return "停止导入数据";
    }


}
