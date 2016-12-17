package cn.xxywithpq.service.rabbitmq;

import cn.xxywithpq.domain.MailInfo;

public interface MessageDelegate {
	void handleRabbitMsgForSendMail(MailInfo mailInfo);
}
