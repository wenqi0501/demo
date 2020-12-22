package com.sxh.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @Description:
 * @Auther: wenqi
 * @Date: 2020/12/18 16:11
 */
public class APP {

    public static void main(String[] args) {
        MessageEventFactory factory = new MessageEventFactory();
        int buffSize = 2;

        Disruptor disruptor = new Disruptor(
                factory,//事件工厂
                buffSize,                            //初始化buffer大小
                Executors.defaultThreadFactory(),     //线程创建工厂
                ProducerType.MULTI,                  //单生产者模式
                new BlockingWaitStrategy()            //等待策略
        );

        disruptor.handleEventsWithWorkerPool(new MessageEventHandler(),
                new MessageEventHandler(),
                new MessageEventHandler(),
                new MessageEventHandler(),
                new MessageEventHandler());

        disruptor.start();

        for (int i=0;i<1000;i++){
            RingBuffer<MessageModel> ringBuffer = disruptor.getRingBuffer();
            long sequence = ringBuffer.next();
            MessageModel messageModel = ringBuffer.get(sequence);
            messageModel.setId(i);
            ringBuffer.publish(sequence);
        }

        disruptor.shutdown();

    }
}
