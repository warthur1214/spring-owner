package com.warthur.demo.main.restful;

import com.warthur.demo.main.config.task.TaskAsync;
import com.warthur.demo.main.domain.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by admin on 2017/3/28.
 */
@Controller
public class MainRestService {

	@Autowired
	private TaskAsync taskAsync;

	@GetMapping("/task")
	@ResponseBody
	public String taskAsync() throws Exception {
		taskAsync.doTaskOne();
		taskAsync.doTaskTwo();
		taskAsync.doTaskThree();
		return "taskAsync";
	}

	@GetMapping("/demo")
	public String demo(Model model) {
		model.addAttribute("demo",new Demo());
		return "demo";
	}

	@PostMapping("/demoAdd")
	public String demoAdd(@Valid Demo demo, BindingResult result, Model model) {
		//有错误信息.
		model.addAttribute("demo",demo);
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();

			return "demo";
		}
		return "/demo";
	}
}
