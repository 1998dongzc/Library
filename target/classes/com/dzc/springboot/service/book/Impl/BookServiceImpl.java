package com.dzc.springboot.service.book.Impl;

import com.dzc.springboot.dao.BookMapper;
import com.dzc.springboot.dao.BorrowMapper;
import com.dzc.springboot.dao.UserMapper;
import com.dzc.springboot.model.Book;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BorrowMapper borrowMapper;
    ;

    //更具页面数来查询对应得10条或不到十条顺序
    @Override
    public List<Book> queryAllBooks(Integer pageno) {
        List<Book> books = mapper.selectBooks(pageno);
        return books;
    }

    //获取书籍个数
    @Override
    public Integer queryCount() {
        int num = mapper.selctCount();
        return num;
    }

    //根据条件查询书籍信息
    @Override
    public List<Book> querySomeBooks(String bookname, String bookauthor, String bookroom, Integer pageno) {
        List<Book> books = mapper.selectBookBySelective(bookname, bookauthor, bookroom, pageno);
        return books;
    }

    @Override
    public Integer querySomeBooksCount(String bookname, String bookauthor, String bookroom, Integer pageno) {
        Integer num = mapper.selectBookCountBySelective(bookname, bookauthor, bookroom, pageno);
        return num;
    }

    @Transactional
    @Override
    public boolean addBook(String name, String author, String price, Integer status, String room, String num) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setStatus(status);
        book.setRoom(room);
        book.setNum(num);
        Book one = mapper.selectOneBook(book);
        if (one == null) {
            mapper.insert(book);
            return true;
        } else
            return false;
    }

    @Override
    public List<Book> getSomeBooks(String bookname, String bookauthor, String bookroom) {
        List<Book> books = mapper.selectBooksnopage(bookname, bookauthor, bookroom);
        return books;
    }

    @Override
    @Transactional
    public boolean doDeleteById(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        if (i == 1)
            return true;
        else
            return false;
    }

    @Override
    public Book seletOneBookById(Integer id) {
        Book book = mapper.selectByPrimaryKey(id);
        return book;
    }

    @Override
    @Transactional
    public int updateOnBook(Book book) {
        int i = mapper.updateByPrimaryKeySelective(book);
        return i;
    }

}