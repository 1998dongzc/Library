package com.dzc.springboot.service.book;

import com.dzc.springboot.model.Book;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/11 9:18
 * @description:
 * @email：532587041@qq.com
 */
public interface BookService {

    List<Book> queryAllBooks();

}
