package com.warthur.demo.shiro.config;

import com.warthur.demo.shiro.domain.RoleInfo;
import com.warthur.demo.shiro.domain.SysPermission;
import com.warthur.demo.shiro.domain.UserInfo;
import com.warthur.demo.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/27.
 */

public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	private UserInfoService userInfoService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo  = (UserInfo)principalCollection.getPrimaryPrincipal();

		for(RoleInfo role:userInfo.getRoleList()){
			authorizationInfo.addRole(role.getRole());
			for(SysPermission p:role.getPermissions()){
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

		String userName = (String) token.getPrincipal();
		System.out.println(token.getPrincipal());

		UserInfo userInfo = userInfoService.findByUserName(userName);
		System.out.println("----->>userInfo="+userInfo);

		if (userInfo == null) {
			return null;
		}

		return new SimpleAuthenticationInfo(
				userInfo.getUserName(), //用户名
				userInfo.getPassword(), //密码
				ByteSource.Util.bytes(userInfo.getSalt()),//salt=username+salt
				getName()  //realm name
		);
	}
}
