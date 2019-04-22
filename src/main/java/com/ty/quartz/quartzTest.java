package com.ty.quartz;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * quartz定时器框架
 *
 * @ClassName quartzTest
 * @Author tianye
 * @Date 2019/4/16 22:38
 * @Version 1.0
 */
public class quartzTest {

    public static void main(String[] args) throws InterruptedException {

//        for (int i = 0; i < 10; i++) {
//            TimeUnit.DAYS.toHours()
//            Thread.sleep(1000);
//            System.out.println("秒数:"+i);
//        }

        long l = TimeUnit.DAYS.toHours(1);

        TimeUnit.MINUTES.sleep(1);
        System.out.println(l);

        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.e


    }
}
