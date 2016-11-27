package cn.xxywithpq.service;

public interface MessageDelegate {
	void handleRabbitMsg(String message);  
}
