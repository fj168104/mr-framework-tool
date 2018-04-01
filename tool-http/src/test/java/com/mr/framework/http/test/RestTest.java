package com.mr.framework.http.test;

import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.lang.Console;
import com.mr.framework.http.HttpRequest;
import com.mr.framework.json.JSONUtil;

public class RestTest {
	
	@Test
	@Ignore
	public void postTest() {
		HttpRequest request = HttpRequest.post("http://localhost:8090/rest/restTest/")
				.body(JSONUtil.createObj().put("aaa", "aaaValue").put("键2", "值2"));
		Console.log(request.execute().body());
	}
}
