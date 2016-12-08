package cn.xxywithpq.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.kitesdk.shaded.com.google.common.collect.Sets;

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

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "icon")
	public Set<User> users = Sets.newLinkedHashSet();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
