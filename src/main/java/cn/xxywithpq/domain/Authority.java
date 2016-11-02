package cn.xxywithpq.domain;

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

import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Lists;

/**
 * 权限表
 * 
 * @author panqian
 * @date 2016年10月27日 下午6:58:12
 */
@Entity
@Table(name = "OAK_AUTHORITIES")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 730125444981434883L;

	@Id
	@GeneratedValue
	public Long id;

	@NotNull
	@Column(name = "AUTHORITY", unique = true, length = 50)
	public String authority;

	@Column(name = "ROLE")
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = cn.xxywithpq.domain.Role.class, mappedBy = "authorities")
	public List<Role> roles = Lists.newArrayList();

	public Authority() {
		super();
	}

	public Authority(Long id, String authority, List<Role> roles) {
		super();
		this.id = id;
		this.authority = authority;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
