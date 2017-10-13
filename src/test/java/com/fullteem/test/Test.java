package com.fullteem.test;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		BigDecimal decimal =new BigDecimal("0.01");
		BigDecimal decimal1 =new BigDecimal("0.01");
		com.fullteem.common.utils.Log.println(decimal.compareTo(decimal1));
	}
}
