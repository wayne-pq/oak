package cn.xxywithpq.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.kitesdk.shaded.com.google.common.collect.Sets;

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
	@Column(name = "email", length = 80)
	public String email;

	@NotNull
	@Column(name = "ENABLED")
	public Boolean enabled;

	@Column(name = "ROLE")
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = cn.xxywithpq.domain.Role.class)
	public Set<Role> roles = Sets.newLinkedHashSet();

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = cn.xxywithpq.domain.Icon.class)
	@JoinColumn(name = "ICON_ID", unique = true, nullable = true, updatable = true)
	public Icon icon;

	public User() {
		super();
	}

	public User(Long id, String username, String password, Boolean enabled, Set<Role> roles) {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
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

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
