package cn.xxywithpq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxywithpq.domain.Role;
import cn.xxywithpq.repository.RoleRepository;

/**
 * 角色管理服务类
 * 
 * @author panqian
 * @date 2016年11月27日 下午5:04:16
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public Role findOne(Long id) {
		return roleRepository.findById(id);
	}

	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

}