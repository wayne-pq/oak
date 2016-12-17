package cn.xxywithpq.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.kitesdk.shaded.com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;

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
			CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = cn.xxywithpq.domain.Role.class, mappedBy = "authorities")
	public Set<Role> roles = Sets.newLinkedHashSet();

	public Authority() {
		super();
	}

	public Authority(Long id, String authority, Set<Role> roles) {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
