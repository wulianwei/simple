package com.org.prac.simple.entity;

import java.util.List;

import lombok.Data;

@Data
public class BookVO {
	private Integer total;
    private List<Book> data;
    private Integer pageSize;
}
