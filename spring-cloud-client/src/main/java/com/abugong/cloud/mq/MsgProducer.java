package com.abugong.cloud.mq;

import org.springframework.stereotype.Service;

@Service
public class MsgProducer{
    
	private Sender sender;
    /**
     * 用于单生产者-》单消费者测试
     * @throws InterruptedException 
     */
    public void send() throws InterruptedException {
    	Long id = 1L;
        while(true){
            Thread.sleep(1000);
            this.sender.send("订单服务");
            id++;
        }
    }

}