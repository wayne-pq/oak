package cn.xxywithpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xxywithpq.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}