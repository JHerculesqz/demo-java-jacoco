package com.huawei.client.provider;

import com.huawei.client.provider.subprovider.Subprovider;

public class MainProvider {
	// #region hello

	public static String hello(String strText) {
		return Subprovider.hello(strText);
	}

	// #endregion
}
