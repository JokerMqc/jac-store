package com.yph.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 任务例子
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/23
 **/
@Slf4j
@Component("demoTask")
public class DemoTask {

    public void test(String params){
        log.info("我是带参数的test方法，正在被执行，参数为：" + params);

        log.info("[     定时任务 执行-------------------------->    ]");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void test2(){
        log.info("我是不带参数的test2方法，正在被执行");
    }
}