package com.mr.framework.json;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.core.collection.CollUtil;
import com.mr.framework.core.collection.CollectionUtil;
import com.mr.framework.json.test.bean.Seq;
import com.mr.framework.json.test.bean.UserA;
import com.mr.framework.json.test.bean.UserB;

/**
 * JSONObject单元测试
 * @author fengj
 *
 */
public class JSONObjectTest {
	
	@Test
	public void putAllTest(){
		JSONObject json1 = JSONUtil.createObj();
		json1.put("a", "value1");
		json1.put("b", "value2");
		json1.put("c", "value3");
		json1.put("d", true);
		
		JSONObject json2 = JSONUtil.createObj();
		json2.put("a", "value21");
		json2.put("b", "value22");
		
		//putAll操作会覆盖相同key的值，因此a,b两个key的值改变，c的值不变
		json1.putAll(json2);
		
		Assert.assertEquals(json1.get("a"), "value21");
		Assert.assertEquals(json1.get("b"), "value22");
		Assert.assertEquals(json1.get("c"), "value3");
	}
	
	@Test
	public void parseStringTest(){
		String jsonStr = "{\"b\":\"value2\",\"c\":\"value3\",\"a\":\"value1\", \"d\": true, \"e\": null}";
		JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
		Assert.assertEquals(jsonObject.get("a"), "value1");
		Assert.assertEquals(jsonObject.get("b"), "value2");
		Assert.assertEquals(jsonObject.get("c"), "value3");
		Assert.assertEquals(jsonObject.get("d"), true);
		
		Assert.assertTrue(jsonObject.containsKey("e"));
		Assert.assertEquals(jsonObject.get("e"), JSONNull.NULL);
	}
	
	@Test
	public void toBeanTest(){
		JSONObject subJson = JSONUtil.createObj().put("value1", "strValue1").put("value2", "234");
		JSONObject json = JSONUtil.createObj()
			.put("strValue", "strTest")
			.put("intValue", 123)
			//测试空字符串转对象
			.put("doubleValue", "")
			.put("beanValue", subJson)
			.put("list", JSONUtil.createArray().put("a").put("b"))
			.put("testEnum", "TYPE_A");
		
		TestBean bean = json.toBean(TestBean.class);
		Assert.assertEquals("a", bean.getList().get(0));
		Assert.assertEquals("b", bean.getList().get(1));
		
		Assert.assertEquals("strValue1", bean.getBeanValue().getValue1());
		//BigDecimal转换检查
		Assert.assertEquals(new BigDecimal("234"), bean.getBeanValue().getValue2());
		//枚举转换检查
		Assert.assertEquals(TestEnum.TYPE_A, bean.getTestEnum());
	}
	
	@Test
	public void toBeanNullStrTest(){
		JSONObject json = JSONUtil.createObj()
				.put("strValue", "null")
				.put("intValue", 123)
				.put("beanValue", "null")
				.put("list", JSONUtil.createArray().put("a").put("b"));
		
		TestBean bean = json.toBean(TestBean.class, true);
		//当JSON中为字符串"null"时应被当作字符串处理
		Assert.assertEquals("null", bean.getStrValue());
		//当JSON中为字符串"null"时Bean中的字段类型不匹配应在ignoreError模式下忽略注入
		Assert.assertEquals(null, bean.getBeanValue());
	}
	
	@Test
	public void toBeanTest2(){
		UserA userA = new UserA();
		userA.setA("A user");
		userA.setName("nameTest");
		userA.setDate(new Date());
		userA.setSqs(CollectionUtil.newArrayList(new Seq("seq1"), new Seq("seq2")));

		JSONObject json = JSONUtil.parseObj(userA);
		UserA userA2 = json.toBean(UserA.class);
		Assert.assertEquals("seq1", userA2.getSqs().get(0).getSeq());
	}
	
	@Test
	public void parseBeanTest(){
		UserA userA = new UserA();
		userA.setName("nameTest");
		userA.setDate(new Date());
		userA.setSqs(CollectionUtil.newArrayList(new Seq(null), new Seq("seq2")));
		
		JSONObject json = JSONUtil.parseObj(userA, false);
		Assert.assertTrue(json.containsKey("a"));
		Assert.assertTrue(json.getJSONArray("sqs").getJSONObject(0).containsKey("seq"));
	}
	
	@Test
	public void parseBeanTest2(){
		TestBean bean = new TestBean();
		bean.setDoubleValue(111.1);
		bean.setIntValue(123);
		bean.setList(CollUtil.newArrayList("a", "b", "c"));
		bean.setStrValue("strTest");
		bean.setTestEnum(TestEnum.TYPE_B);
		
		JSONObject json = JSONUtil.parseObj(bean, false);
		//枚举转换检查
		Assert.assertEquals("TYPE_B", json.get("testEnum"));
		
		TestBean bean2 = json.toBean(TestBean.class);
		Assert.assertEquals(bean.toString(), bean2.toString());
	}
	
	@Test
	public void beanTransTest(){
		UserA userA = new UserA();
		userA.setA("A user");
		userA.setName("nameTest");
		userA.setDate(new Date());
		
		JSONObject userAJson = JSONUtil.parseObj(userA);
		UserB userB = JSONUtil.toBean(userAJson, UserB.class);
		
		Assert.assertEquals(userA.getName(), userB.getName());
		Assert.assertEquals(userA.getDate(), userB.getDate());
	}
	
	@Test
	public void parseFromBeanTest(){
		UserA userA = new UserA();
		userA.setA(null);
		userA.setName("nameTest");
		userA.setDate(new Date());
		
		JSONObject userAJson = JSONUtil.parseObj(userA);
		Assert.assertFalse(userAJson.containsKey("a"));
		
		JSONObject userAJsonWithNullValue = JSONUtil.parseObj(userA, false);
		Assert.assertTrue(userAJsonWithNullValue.containsKey("a"));
		Assert.assertTrue(userAJsonWithNullValue.containsKey("sqs"));
	}
	
	@Test
	public void specialCharTest() {
		String json = "{\"pattern\": \"[abc]\b\u2001\", \"pattern2Json\": {\"patternText\": \"[ab]\\b\"}}";
		JSONObject obj = JSONUtil.parseObj(json);
		Assert.assertEquals("[abc]\\b\\u2001", obj.getStr("pattern"));
		Assert.assertEquals("{\"patternText\":\"[ab]\\b\"}", obj.getStr("pattern2Json"));
	}
	
	public static enum TestEnum{
		TYPE_A, TYPE_B
	}
	
	/**
	 * 测试Bean
	 * @author fengj
	 *
	 */
	public static class TestBean{
		private String strValue;
		private int intValue;
		private Double doubleValue;
		private subBean beanValue;
		private List<String> list;
		private TestEnum testEnum;
		
		public String getStrValue() {
			return strValue;
		}
		public void setStrValue(String strValue) {
			this.strValue = strValue;
		}
		public int getIntValue() {
			return intValue;
		}
		public void setIntValue(int intValue) {
			this.intValue = intValue;
		}
		public Double getDoubleValue() {
			return doubleValue;
		}
		public void setDoubleValue(Double doubleValue) {
			this.doubleValue = doubleValue;
		}
		public subBean getBeanValue() {
			return beanValue;
		}
		public void setBeanValue(subBean beanValue) {
			this.beanValue = beanValue;
		}
		public List<String> getList() {
			return list;
		}
		public void setList(List<String> list) {
			this.list = list;
		}
		public TestEnum getTestEnum() {
			return testEnum;
		}
		public void setTestEnum(TestEnum testEnum) {
			this.testEnum = testEnum;
		}
		@Override
		public String toString() {
			return "TestBean [strValue=" + strValue + ", intValue=" + intValue + ", doubleValue=" + doubleValue + ", beanValue=" + beanValue + ", list=" + list + ", testEnum=" + testEnum + "]";
		}
	}
	
	/**
	 * 测试子Bean
	 * @author fengj
	 *
	 */
	public static class subBean{
		private String value1;
		private BigDecimal value2;
		
		public String getValue1() {
			return value1;
		}
		public void setValue1(String value1) {
			this.value1 = value1;
		}
		public BigDecimal getValue2() {
			return value2;
		}
		public void setValue2(BigDecimal value2) {
			this.value2 = value2;
		}
		@Override
		public String toString() {
			return "subBean [value1=" + value1 + ", value2=" + value2 + "]";
		}
	}
}
