package com.dzc.springboot.service.book.Impl;

import com.dzc.springboot.dao.BookMapper;
import com.dzc.springboot.model.Book;
import com.dzc.springboot.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/11 9:19
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper mapper;

    @Override
    public List<Book> queryAllBooks() {
        List<Book> books = mapper.selectAllBook();
        return books;
    }


}
