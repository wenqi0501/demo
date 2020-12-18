package com.sxh.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Description:
 * @Auther: wenqi
 * @Date: 2020/12/17 17:40
 */
public class MessageEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
