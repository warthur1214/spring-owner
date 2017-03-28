package com.warthur.demo.test.service.impl;

import com.warthur.demo.test.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by admin on 2017/3/28.
 */
@Service
@Profile("product") // 生产环境
public class ProductEmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void send() {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("warthur@163.com");//发送者.
			helper.setTo("wuyongqiang@chinaubi.com");//接收者.
			helper.setSubject("测试静态资源（邮件主题）");//邮件主题.
			// 邮件内容，第二个参数指定发送的是HTML格式
			//说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
			helper.setText("<body>这是图片：<img src='cid:head' /></body>", true);

			FileSystemResource file = new FileSystemResource(new File("C:\\Users\\admin\\Pictures\\22222.jpg"));
			helper.addInline("head", file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}


		mailSender.send(mimeMessage);
	}
}
