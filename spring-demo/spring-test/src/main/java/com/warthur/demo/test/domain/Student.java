package com.warthur.demo.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Student {

	private String stuNo;
	private String stuName;
	private int age;
	private List<Integer> score;
	@XmlElementWrapper(name = "books")
	@XmlElement(name = "book")
	private List<Book> books;
}
