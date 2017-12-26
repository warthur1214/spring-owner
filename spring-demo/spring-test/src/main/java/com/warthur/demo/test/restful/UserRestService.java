package com.warthur.demo.test.restful;

import com.warthur.demo.test.config.property.MyProperties;
import com.warthur.demo.test.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by admin on 2017/3/28.
 */
@RestController
@RequestMapping("/user")
public class UserRestService {

	private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	@Autowired
	private MyProperties properties;

	@GetMapping("")
	public List<User> getUserList() {
		return new ArrayList<>(users.values());
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return users.get(id);
	}

	@GetMapping("/success")
	public String success() {
		return "success22";
	}

	@PutMapping("/{id}")
	public String updateUserById(@PathVariable("id") Long id, User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());

		users.put(id, u);
		return "success";
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		// url中的id可通过@PathVariable绑定到函数的参数中
		users.remove(id);
		return"success";
	}

	@GetMapping("/my")
	public String property() {
		System.out.println(1111);
		return "hello world!";
	}
}
