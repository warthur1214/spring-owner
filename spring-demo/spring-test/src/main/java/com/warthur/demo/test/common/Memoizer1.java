package com.warthur.demo.test.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by warth on 2018/2/27.
 */
public class Memoizer1<A, V> implements Computable<A, V> {

	// 线程安全
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
	private final Computable<A, V> computable;

	public Memoizer1(Computable<A, V> computable) {
		this.computable = computable;
	}

	@Override
	public V doCompute(final A arg) throws Exception {
		while (true) {
			Future<V> future = cache.get(arg);
			if (future == null) {
				FutureTask<V> ft = new FutureTask<>(() -> computable.doCompute(arg));

				future = cache.putIfAbsent(arg, ft);
				if (future == null) {
					future = ft;
					ft.run();
				}
			}

			try {
				return future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
				cache.remove(arg, future);
			} catch (ExecutionException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
}
