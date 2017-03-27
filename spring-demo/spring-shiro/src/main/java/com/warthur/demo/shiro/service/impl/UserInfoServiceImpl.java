package com.warthur.demo.shiro.service.impl;

import com.warthur.demo.shiro.domain.UserInfo;
import com.warthur.demo.shiro.repository.UserInfoRepository;
import com.warthur.demo.shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/27.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo findByUserName(String userName) {
		return userInfoRepository.findByUserName(userName);
	}
}
