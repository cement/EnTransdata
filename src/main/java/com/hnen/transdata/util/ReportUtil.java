package com.hnen.transdata.util;

import java.util.Date;

public class ReportUtil {
    /**
     * @author YSH
     * @param reportKind 举报类别(1.信件，2.电话，3.微信)
     * @param areaId 市县区ID( 6位)
     * @return 生成的举报编号
     */
    // 生成举报编号 ： 规则 : ('A' + 举报类别(1.信件，2.电话，3.微信) + areaid( 6位) + 时间戳)

    public static String makeReportCode(String reportKind,String areaId){
        long ts = new Date().getTime();
        String res = String.valueOf(ts);
        return 'A' + reportKind + areaId + res;
    }
    // 生成微信举报编号
    public static String makeWxReportCode(String areaId){
        long time = new Date().getTime();
        String res = String.valueOf(time);
        return 'A' + "3" + areaId + res;
    }
}
