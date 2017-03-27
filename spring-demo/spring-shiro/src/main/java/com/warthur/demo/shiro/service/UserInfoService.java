package com.warthur.demo.shiro.service;

import com.warthur.demo.shiro.domain.UserInfo;

/**
 * Created by admin on 2017/3/27.
 */
public interface UserInfoService {

	UserInfo findByUserName(String userName);
}
