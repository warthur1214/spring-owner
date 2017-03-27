package com.warthur.demo.shiro.config;

import com.warthur.demo.shiro.config.filter.CustomFormAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by admin on 2017/3/27.
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * cookie 对象
	 * @return
	 */
	@Bean
	public SimpleCookie rememberCookie() {
		System.out.println("ShiroConfiguration.rememberMeCookie()");

		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(7 * 86400);
		return cookie;
	}

	/**
	 * cookie管理对象;
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
		System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberCookie());
		return cookieRememberMeManager;
	}

	/**
	 * shiro 缓存管理器
	 * @return
	 */
	@Bean
	public EhCacheManager ehCacheManager(){
		System.out.println("ShiroConfiguration.getEhCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
	}

	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		//设置realm.
		securityManager.setRealm(myShiroRealm());

		//注入缓存对象
		securityManager.setCacheManager(ehCacheManager());

		//注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());;
		return myShiroRealm;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

		return hashedCredentialsMatcher;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 Filter Chain定义说明
	 1、一个URL可以配置多个Filter，使用逗号分隔
	 2、当设置多个过滤器时，全部验证通过，才视为通过
	 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		filterMap.put("authc", new CustomFormAuthenticationFilter());

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

		//配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");

		//验证码可以匿名访问
		filterChainDefinitionMap.put("/validatecodeServlet", "anon");

		//配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/index", "user");
		filterChainDefinitionMap.put("/", "user");

		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

}
