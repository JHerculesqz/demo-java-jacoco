package com.huawei.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JacocoClientController {
	// #region Fields

	@Autowired
	private JacocoClientService jacocoClientService;

	// #endregion

	// #region hello

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(@RequestBody String strText) {
		return this.jacocoClientService.hello(strText);
	}

	// #endregion
}
