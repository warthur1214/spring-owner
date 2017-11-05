package com.warthur.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.warthur.demo.test.domain.User;
import com.warthur.demo.test.restful.UserRestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by admin on 2017/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MockServletContext.class)
public class UserRestServiceTest extends MockMvcResultMatchers {

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(new UserRestService()).build();
	}

	@Test
	public void testJsonConvertObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user_id", 123111);
		jsonObject.put("user_name", "warthur");
		jsonObject.put("user_age", 27);

		User user = JSONObject.parseObject(jsonObject.toJSONString(), User.class);

		Assert.assertEquals(User.class, user.getClass());
	}

	@Test
	public void testUserRestService() throws Exception{
		RequestBuilder request = null;
		//1. get 以下user列表，应该为空》

		//1、构建一个get请求.
		request = MockMvcRequestBuilders.get("/user");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("[]"))
		;
		System.out.println("UserControllerTest.testUserController().get");

		// 2、post提交一个user
		request = MockMvcRequestBuilders.post("/user")
				.param("id","1")
				.param("name","林峰")
				.param("age","20")
		;


		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("success"));

		// 3、get获取user列表，应该有刚才插入的数据
		request = MockMvcRequestBuilders.get("/user");
		mvc.perform(request).andExpect(status().isOk()).andExpect(
				content().string("[{\"id\":1,\"name\":\"林峰\",\"age\":20}]"));


		// 4、put修改id为1的user
		request = MockMvcRequestBuilders.put("/user/1")
				.param("name", "林则徐")
				.param("age", "30");
		mvc.perform(request)
				.andExpect(content().string("success"));

		// 5、get一个id为1的user
		request = MockMvcRequestBuilders.get("/user/1");
		mvc.perform(request)
				.andExpect(content().string("{\"id\":1,\"name\":\"林则徐\",\"age\":30}"));



		// 6、del删除id为1的user
		request = MockMvcRequestBuilders.delete("/user/1");
		mvc.perform(request)
				.andExpect(content().string("success"));

		// 7、get查一下user列表，应该为空
		request = MockMvcRequestBuilders.get("/user");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));

	}
}
