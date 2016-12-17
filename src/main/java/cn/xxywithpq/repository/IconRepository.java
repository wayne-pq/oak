package cn.xxywithpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xxywithpq.domain.Icon;

public interface IconRepository extends JpaRepository<Icon, String> {
}