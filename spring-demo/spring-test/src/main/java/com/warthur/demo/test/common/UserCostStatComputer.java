package com.warthur.demo.test.common;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * Created by warth on 2018/2/27.
 */
public class UserCostStatComputer implements Computable<String, BigInteger> {

	@Override
	public BigInteger doCompute(String userId) throws InterruptedException {
		// assume it cost a long time...
		TimeUnit.SECONDS.sleep(3);
		return new BigInteger(userId);
	}
}
