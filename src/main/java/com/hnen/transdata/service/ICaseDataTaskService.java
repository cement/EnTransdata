package com.hnen.transdata.service;

import java.io.IOException;

public interface ICaseDataTaskService {

    public void  importCaseUnitInfo() throws IOException;

    public void importCaseBaseInfo() throws IOException;
    public void importCasePeopleInfo() throws IOException;
    public void importCasePunishInfo() throws IOException;
    public void importCaseRecordInfo() throws IOException;
    public void importCaseReceiveInfo() throws IOException;
}
