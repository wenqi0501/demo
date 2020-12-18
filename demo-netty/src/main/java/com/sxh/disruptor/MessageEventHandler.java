package com.sxh.disruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: wenqi
 * @Date: 2020/12/17 17:40
 */
public class MessageEventHandler implements WorkHandler<MessageModel> {

    @Override
    public void onEvent(MessageModel event) throws Exception {
        System.out.println(">>>>>>>>>>>>>执行event事件线程名称"+Thread.currentThread().getName()+"|"+event.getId());
        TimeUnit.SECONDS.sleep(1);
    }
}
