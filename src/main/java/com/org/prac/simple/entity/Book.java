package com.org.prac.simple.entity;

public class Book {
	private int id;
	private String name;
	private String author;
	private String publish;
	private int pages;
	private double price;
	private int bookcaseid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBookcaseid() {
		return bookcaseid;
	}
	public void setBookcaseid(int bookcaseid) {
		this.bookcaseid = bookcaseid;
	}
	
	

}
