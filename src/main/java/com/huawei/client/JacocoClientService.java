package com.huawei.client;

import org.springframework.stereotype.Service;

import com.huawei.client.provider.MainProvider;

@Service
public class JacocoClientService {
	// #region hello

	public String hello(String strText) {
		return MainProvider.hello(strText);
	}

	// #endregion
}
