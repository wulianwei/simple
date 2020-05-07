package com.org.prac.simple.mapper;

import java.util.List;

import com.org.prac.simple.entity.Book;

public interface BookMapper {
	public List<Book> find(Integer index,Integer limit);
    public Integer count();

}
