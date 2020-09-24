package com.dzc.springboot.service.book;

import com.dzc.springboot.model.Book;
import com.dzc.springboot.model.User;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/11 9:18
 * @description:
 * @email：532587041@qq.com
 */
public interface BookService {

    List<Book> queryAllBooks(Integer pageno);

    Integer queryCount();

    List<Book> querySomeBooks(String bookname, String bookauthor, String bookroom,Integer pageno);

    Integer querySomeBooksCount(String bookname, String bookauthor, String bookroom,Integer pageno);

    boolean addBook(String name, String author, String price, Integer status, String room, String num);

    List<Book> getSomeBooks(String bookname,String bookauthor,String bookroom);

    boolean doDeleteById(Integer id);

    Book seletOneBookById(Integer id);

    int updateOnBook(Book book);

}

