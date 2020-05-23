package com.org.prac.simple.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
	private int id;
	private String name;
	private String author;
	private String publish;
	private int pages;
	private double price;
	private int bookcaseid;
}
