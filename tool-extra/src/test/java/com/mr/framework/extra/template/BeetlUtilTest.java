package com.mr.framework.extra.template;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.core.lang.Dict;

/**
 * BeetlUtil单元测试
 * 
 * @author fengj
 *
 */
public class BeetlUtilTest {

	@Test
	public void renderStrTest() throws IOException {
		GroupTemplate groupTemplate = BeetlUtil.createGroupTemplate(new StringTemplateResourceLoader(), Configuration.defaultConfiguration());
		Template template = BeetlUtil.getTemplate(groupTemplate, "hello,${name}");
		String result = BeetlUtil.render(template, Dict.create().set("name", "tool"));

		Assert.assertEquals("hello,tool", result);

		String renderFromStr = BeetlUtil.renderFromStr("hello,${name}", Dict.create().set("name", "tool"));
		Assert.assertEquals("hello,tool", renderFromStr);

	}
}
