package com.huawei.client.provider.subprovider;

public class Subprovider {
	// #region hello

	public static String hello(String strText) {
		if (strText.contains("1")) {
			System.out.println("true...");
			return "true";
		} else {
			System.out.println("false...");
			return "false";
		}
	}

	// #endregion
}
