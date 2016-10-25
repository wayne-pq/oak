package cn.xxywithpq.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/work/")
public class WorkController {
	@RequestMapping(method = RequestMethod.POST, value = "simple")
	public Object Login(@RequestParam(name = "msg", required = false) String msg) {
		JSONObject object = new JSONObject();
		System.out.println(msg);
		object.put("msg", "work-ok");
		return object;
	}
	@RequestMapping(method = RequestMethod.GET, value = "simple")
	public Object LoginGet(@RequestParam(name = "msg", required = false) String msg) {
		JSONObject object = new JSONObject();
		System.out.println(msg);
		object.put("msg", "work-ok");
		return object;
	}
}
