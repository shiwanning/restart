package com.restart.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("BusinessTask")
public class BusinessTask {


    public static  final Logger LOGGER = LoggerFactory.getLogger(BusinessTask.class);
    //select sum(ot.num) from orders ot where ot.create_time > DATE_FORMAT(NOW(), '%Y-%m-%d') GROUP BY ot.business_id;

    public void synNumber(){
        LOGGER.info("已同步");
    }
}
