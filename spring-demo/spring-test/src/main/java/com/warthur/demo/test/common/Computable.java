package com.warthur.demo.test.common;

/**
 * Created by warth on 2018/2/27.
 */
public interface Computable<A, V> {

	V doCompute(A arg) throws Exception;

}
