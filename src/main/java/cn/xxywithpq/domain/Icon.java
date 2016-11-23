package cn.xxywithpq.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OAK_ICON")
public class Icon implements Serializable {

	private static final long serialVersionUID = 3579068848070332393L;

	@Id
	public String id;

	@NotNull
	@Column(name = "PATH", length = 120)
	public String path;

	@NotNull
	@Column(name = "IPADDR", length = 50)
	public String ipAddr;

	@OneToOne(mappedBy = "icon", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	public User user;

	public Icon() {
	}

	public Icon(String id, String path, String ipAddr) {
		this.id = id;
		this.path = path;
		this.ipAddr = ipAddr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
