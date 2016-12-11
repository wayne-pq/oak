package cn.xxywithpq.domain;

import java.io.Serializable;

/**
 * 激活邮件信息类
 * 
 * @author panqian
 * @date 2016年12月11日 下午2:32:20
 */
public class MailInfo implements Serializable {

	private static final long serialVersionUID = -5306364187336291767L;

	private String id;

	private String userName;

	private String setTo;

	private String activeCode;

	public MailInfo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSetTo() {
		return setTo;
	}

	public void setSetTo(String setTo) {
		this.setTo = setTo;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

}
