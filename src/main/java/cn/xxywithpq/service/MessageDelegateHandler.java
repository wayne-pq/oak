package cn.xxywithpq.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MessageDelegateHandler implements MessageDelegate {

	private static final Log logger = LogFactory.getLog(MessageDelegateHandler.class);

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void handleRabbitMsg(String msg) {
		logger.info("rabbit--一条短信息被接受：" + msg);
		sendmail(msg);
	}
	
	public void sendmail(String msg) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setTo("994971838@qq.com");
			helper.setSubject("welcome to oak : "+ msg);
			helper.setText("欢迎来到oak");
			helper.setFrom("455234037@qq.com");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
		logger.info("邮件发送成功。。。。。。");
	}
	
}
