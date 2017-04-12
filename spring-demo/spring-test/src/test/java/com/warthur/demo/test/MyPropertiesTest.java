package com.warthur.demo.test;

import com.warthur.demo.test.config.property.MyProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by admin on 2017/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class MyPropertiesTest {

	@Autowired
	private MyProperties myProperties;

	@Test
	public void testMyProperties() throws Exception {
		System.out.println("AppTest.testBlog()=" + myProperties);
		Assert.assertEquals("吴永强", myProperties.getName());
		Assert.assertEquals(26, myProperties.getAge());
		Assert.assertEquals("吴永强： 26", myProperties.getDesc());
		Assert.assertEquals("left", myProperties.getHands().get(0));
		Assert.assertEquals("lin", myProperties.getGirl().getName());
		System.out.println(myProperties.getHands().get(0));
	}
}
