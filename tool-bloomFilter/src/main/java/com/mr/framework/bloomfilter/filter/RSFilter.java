package com.mr.framework.bloomfilter.filter;

import com.mr.framework.core.util.HashUtil;

public class RSFilter extends AbstractFilter {

	public RSFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public RSFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashUtil.rsHash(str) % size;
	}

}