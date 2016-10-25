package cn.xxywithpq.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

/**
 * 用户类
 * 
 * @author panqian
 * @date 2016年10月25日 下午1:35:17
 */
@Entity
@Table(name = "OAK_USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -486521137267305213L;

	@Id
	@GeneratedValue
	public Long id;

	@NotNull
	@Column(name = "USERNAME", length = 50)
	public String username;

	@NotNull
	@Column(name = "PASSWORD", length = 80)
	public String password;

	@NotNull
	@Column(name = "ENABLED")
	public Boolean enabled;

	@Column(name = "ROLE")
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = cn.xxywithpq.domain.Role.class)
	public List<Role> roles = Lists.newArrayList();

	public User() {
		super();
	}

	public User(Long id, String username, String password, Boolean enabled, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
