package com.hnen.transdata.runner;

import com.hnen.transdata.task.CaseDataTask;
import com.hnen.transdata.task.HsmDataTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TransDataRunner  implements ApplicationRunner {

    @Value("${hsm.isbootup:true}")
    public   boolean isHsmBootup ;
    @Value("${case.isbootup:true}")
    public   boolean isCaseBootup ;
    @Autowired
    private HsmDataTask hsmTask;

    @Autowired
    private CaseDataTask caseTask;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isHsmBootup){
            hsmTask.startAllTask();
        }
        if (isCaseBootup) {
            caseTask.startAllTask();
        }
    }
}
