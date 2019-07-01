package com.restart.task;


import org.springframework.stereotype.Component;

@Component("BusinessTask")
public class BusinessTask {

    //select sum(ot.num) from orders ot where ot.create_time > DATE_FORMAT(NOW(), '%Y-%m-%d') GROUP BY ot.business_id;

    public void sycnNumber(){

    }
}
