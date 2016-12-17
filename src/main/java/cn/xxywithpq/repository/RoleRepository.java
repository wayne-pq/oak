package cn.xxywithpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xxywithpq.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findById(Long id);
}