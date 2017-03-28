package com.warthur.demo.test.service.impl;

import com.warthur.demo.test.service.EmailService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/3/28.
 */
@Service
@Profile("dev") // 开发环境
public class DevEmailServiceImpl implements EmailService {

	@Override
	public void send() {
		System.out.println("开发环境不执行邮件发送逻辑");
	}
}
