package com.statestreet.mq.HelloRocketMQ;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer {
	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("group");
		producer.setNamesrvAddr("192.168.230.130:9876");
		producer.start();
		for (int i = 0; i < 20; i++) {
			Message msg = new Message("orders",("order_" + i).getBytes());
			SendResult result = producer.send(msg);
//			System.out.println(result);
			Thread.sleep(500);
		}
		producer.shutdown();
		
	}
}
