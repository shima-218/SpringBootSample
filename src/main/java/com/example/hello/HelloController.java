package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //コントローラークラスを示すアノテーション
public class HelloController {
	@Autowired
	private HelloService service;

	@GetMapping("/hello") //GETメソッドのHTTPリクエストを受け付けるためのアノテーション
	public String getHello() {
		return "hello";//htmlファイル名を戻り値にする
	}

	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1") String str, Model model){
		model.addAttribute("sample",str);
		return "hello/response";
	}

	@PostMapping("hello/db")
	public String postDbRequest(@RequestParam("text2") String id, Model model) {
		Employee employee = service.getEmployee(id);
		model.addAttribute("employee",employee);
		return "hello/db";
	}
}
