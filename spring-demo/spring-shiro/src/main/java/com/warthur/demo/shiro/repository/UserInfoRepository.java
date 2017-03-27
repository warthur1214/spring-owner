package com.warthur.demo.shiro.repository;

import com.warthur.demo.shiro.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by admin on 2017/3/27.
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

	/**通过username查找用户信息;*/
	UserInfo findByUserName(String userName);
}
