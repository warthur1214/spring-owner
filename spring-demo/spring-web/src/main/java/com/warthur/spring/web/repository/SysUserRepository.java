package com.warthur.spring.web.repository;

import com.warthur.spring.web.pojo.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	SysUser findByUserName(String userName);
}
