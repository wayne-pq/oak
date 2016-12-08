package cn.xxywithpq.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Component
public class MessageDelegateHandler implements MessageDelegate {

	private static final Log logger = LogFactory.getLog(MessageDelegateHandler.class);

	private static final String UTF8 = "UTF-8";
	
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
			helper.setTo("1947808424@qq.com");
			helper.setSubject("welcome to oak : "+ msg);
			
			Resource res=new ClassPathResource("mail/mail.html");
			ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
			resolver.setPrefix("/mail/");
			resolver.setSuffix(".html");
			resolver.setCharacterEncoding("ISO-8859-1");
			resolver.setTemplateMode(TemplateMode.HTML);
			
			TemplateEngine templateEngine = new TemplateEngine();
			templateEngine.setTemplateResolver(resolver);
			
			Context context = new Context();
			SimpleDateFormat format = new SimpleDateFormat("YYYY年MM月dd日");
			String now = format.format(new Date());
			try {
				context.setVariable("content", new String("待续".getBytes(),"ISO-8859-1"));
				context.setVariable("now", new String(now.getBytes(),"ISO-8859-1"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String html = templateEngine.process("mail", context);
			helper.setText(html,true);
			helper.setFrom("455234037@qq.com");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
		logger.info("邮件发送成功。。。。。。");
	}
	
}
