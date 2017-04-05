package com.warthur.cloud.zuul.config.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by admin on 2017/4/5.
 */
public class AccessFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		return null;
	}
}
