package com.warthur.spring.web.service;

import com.warthur.spring.web.pojo.SysUser;
import com.warthur.spring.web.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private SysUserRepository sysUserRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		SysUser user = sysUserRepository.findByUserName(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		System.out.println("s:"+s);
		System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
		return user;
	}
}
