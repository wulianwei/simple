package com.org.prac.simple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.prac.simple.entity.Book;
import com.org.prac.simple.entity.BookVO;
import com.org.prac.simple.mapper.BookMapper;
import com.org.prac.simple.service.BookService;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	private Integer limit = 10;
	@Override
	public BookVO findByPage(Integer page) {
		// TODO Auto-generated method stub
		Integer index = (page-1)*limit;
		BookVO vo = new BookVO();
		List<Book> books = bookMapper.find(index, limit);
		Integer count = bookMapper.count();
		vo.setData(books);;
		vo.setTotal(count);
		return vo;
	}

}
