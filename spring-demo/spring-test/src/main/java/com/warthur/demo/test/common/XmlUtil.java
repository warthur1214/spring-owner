package com.warthur.demo.test.common;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

public class XmlUtil {

	public static void parseXml(URL url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url.openStream());
			NodeList bookList = doc.getElementsByTagName("book");
			System.out.println(bookList.getLength());

			for (int i = 0; i < bookList.getLength(); i++) {
				// NamedNodeMap bookAttrMap = bookList.item(i).getAttributes();
				// for (int j = 0; j < bookAttrMap.getLength(); j++) {
				// 	System.out.print("属性名：" + bookAttrMap.item(i).getNodeName());
				// 	System.out.println("属性值：" + bookAttrMap.item(i).getNodeValue());
				// }

				NodeList book = bookList.item(i).getChildNodes();
				for (int j = 0; j < book.getLength(); j++) {
					if (book.item(j).getNodeType() == Node.ELEMENT_NODE) {
						System.out.print("属性： " + book.item(j).getNodeName());
						System.out.println("值： " + book.item(j).getFirstChild().getNodeValue());
					}
				}
				System.out.println("======================");
			}

		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

	}

	public static String toXml(Object object) {
		StringWriter sw = new StringWriter();

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, sw);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

		return sw.toString();
	}

	public static Object parseObject(String xml, Class clazz) {
		Object xmlObject;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader sr = new StringReader(xml);
			xmlObject = unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

		return xmlObject;
	}
}
