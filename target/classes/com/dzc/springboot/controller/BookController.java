package com.dzc.springboot.controller;

import com.dzc.springboot.model.Book;
import com.dzc.springboot.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/9 9:08
 * @description:
 * @email：532587041@qq.com
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    //查询书籍页面跳转
    @RequestMapping("/query")
    public String  toBooks(Model model) {
        //先将所有书籍查出来返回
        List<Book> books = service.queryAllBooks();
        System.out.println(books);
        model.addAttribute("list",books);
        return "/book/books";
    }

}
