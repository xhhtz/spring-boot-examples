package cn.xhhtz.sbe.service.impl;

import cn.xhhtz.sbe.service.AsyncService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * @author lxh
 * @date 2020/10/23 11:09
 */
@Service
public class AsyncServiceImpl implements AsyncService{

    @Resource
    Executor asyncServiceExecutor;

    @Override
//    @Async("asyncServiceExecutor")
    public void asyncWork(String data) {
        asyncServiceExecutor.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println(  Thread.currentThread().getName()+"do work-" + data + "");
                try {
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("work-" + data + "completed");
            }
        });
    }

}
