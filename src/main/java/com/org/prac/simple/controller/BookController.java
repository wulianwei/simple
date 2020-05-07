package com.org.prac.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.prac.simple.entity.BookVO;
import com.org.prac.simple.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("findByPage/{page}")
	public BookVO findByPage(@PathVariable("page") Integer page) {
		return bookService.findByPage(page);
	}
}
