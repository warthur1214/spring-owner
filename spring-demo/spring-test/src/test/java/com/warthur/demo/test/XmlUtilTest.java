package com.warthur.demo.test;

import com.warthur.demo.test.common.XmlUtil;
import com.warthur.demo.test.domain.Book;
import com.warthur.demo.test.domain.Student;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

public class XmlUtilTest {

	@Test
	public void testReadXmlFile() {
		URL url = this.getClass().getClassLoader().getResource("xml/book.xml");
		XmlUtil.parseXml(url);
	}

	@Test
	public void testToXml() {
		Student arthur = Student.builder()
				.stuName("warthur")
				.stuNo("10140906026")
				.age(27)
				.score(Arrays.asList(89, 98, 67))
				.books(Collections.singletonList(Book.builder().bookName("C语言").price(68.7f).build()))
				.build();
		String xml = XmlUtil.toXml(arthur);
		Assert.assertNotNull(xml);
	}
}
