package bhz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bhz.entity.Mail;
import bhz.mq.MQProducer;


/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * @author bhz（Alienware）
 * @since 2014年7月2日
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestProducer {
	
	@Autowired
	private MQProducer mqProducer;
	
	@Test
	public void send(){
		Mail mail = new Mail();
		mail.setTo("174754613@qq.com");
		mail.setSubject("异步发送邮件");
		mail.setContent("Hi,This is a message!");
														
		this.mqProducer.sendMessage(mail);
		System.out.println("发送成功..");		
		
	}

}
