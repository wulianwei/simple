package com.org.prac.simple.entity;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookVO {
	private Integer total;
    private List<Book> data;
    private Integer pageSize;
}
