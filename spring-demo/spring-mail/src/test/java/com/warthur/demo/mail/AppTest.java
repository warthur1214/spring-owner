package com.warthur.demo.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MailApplication.class)
public class AppTest {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 修改application.properties的用户，才能发送。
	 */
	@Test
	public void sendSimpleEmail(){
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("warthur@163.com");//发送者.
		message.setTo("wuyongqiang@chinaubi.com");//接收者.
		message.setSubject("测试邮件（邮件主题）");//邮件主题.
		message.setText("这是邮件内容");//邮件内容.

		mailSender.send(message);//发送邮件
	}

	@Test
	public void sendAttachmentsEmail() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("warthur@163.com");
		helper.setTo("wuyongqiang@chinaubi.com");
		helper.setSubject("测试附件（邮件主题）");//邮件主题.
		helper.setText("这是邮件内容（有附件哦.）");//邮件内容.

		FileSystemResource resource = new FileSystemResource(
				new File("C:\\Users\\admin\\Pictures\\22222.jpg")
		);

		helper.addAttachment(resource.getFilename(), resource);
		mailSender.send(mimeMessage);
	}

	@Test
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("warthur@163.com");//发送者.
		helper.setTo("wuyongqiang@chinaubi.com");//接收者.
		helper.setSubject("测试静态资源（邮件主题）");//邮件主题.
		// 邮件内容，第二个参数指定发送的是HTML格式
		//说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
		helper.setText("<body>这是图片：<img src='cid:head' /></body>", true);

		FileSystemResource file = new FileSystemResource(new File("C:\\Users\\admin\\Pictures\\22222.jpg"));
		helper.addInline("head", file);
		mailSender.send(mimeMessage);
	}

	@Test
	public void sendTemplateMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("warthur@163.com");
		helper.setTo("wuyongqiang@chinaubi.com");
		helper.setSubject("模板邮件（邮件主题）");

		Map<String, Object> model = new HashMap<>();
		model.put("userName", "吴永强");
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = configuration.getTemplate("email.ftl");

		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		helper.setText(html, true);

		mailSender.send(mimeMessage);
	}
}
