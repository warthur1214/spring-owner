package com.warthur.demo.shiro.controller;

import com.warthur.demo.shiro.util.ValidateCode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by admin on 2017/3/27.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	@GetMapping("/userList")
	public String userList(){
		return "userInfo";
	}

	@GetMapping("/userAdd")
	@RequiresPermissions("userInfo:add")
	public String userAdd(){
		return "userInfoAdd";
	}

	@GetMapping("/userDel")
	@RequiresPermissions("userInfo:del")//权限管理;
	public String userDel(){
		return "userInfoDel";
	}

	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}
}
