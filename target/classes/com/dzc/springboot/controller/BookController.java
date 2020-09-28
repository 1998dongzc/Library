package com.dzc.springboot.controller;

import com.dzc.springboot.model.Book;
import com.dzc.springboot.model.Borrow;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.book.BookService;
import com.dzc.springboot.service.borrow.BorrowService;
import com.dzc.springboot.util.DateUtil;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.util.math.IntegerModuloP;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
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

    @Autowired
    private BorrowService borrowService;

    //查询书籍页面跳转 先查询出10条数据放在首页
    @RequestMapping("/querypage")
    public String toBooks(Model model) {
        //先查询10条书籍信息 查出来放到分页显示中的首页
        List<Book> books = service.queryAllBooks(1);
        Integer count = service.queryCount();
        count = count / 10 + 1;
        model.addAttribute("pageno", 1);
        model.addAttribute("list", books);
        model.addAttribute("count", count);
        return "/book/books";
    }

    //curd（增删查改）页面跳转
    @RequestMapping("/curd/create")
    public String createHtml() {
        return "/book/curdhtml/create";
    }

    @RequestMapping("/curd/update")
    public String updateHtml(Model model, Integer id) {
        Book book = service.seletOneBookById(id);
        model.addAttribute("book", book);
        return "/book/curdhtml/update";
    }

    @RequestMapping("/curd/delete")
    public String deleteHtml() {
        return "/book/curdhtml/delete";
    }

    //页面跳转的数据传递
    //无条件下 分页页面跳转(上一页下一页首页末页功能)
    @RequestMapping("/query")
    public String selectQueryPage(Integer pageno, Model model) {
        List<Book> books = service.queryAllBooks(pageno);
        Integer num = service.queryCount();
        int count;
        if (num % 10 == 0)
            count = num / 10;
        else
            count = num / 10 + 1;
        model.addAttribute("list", books);
        //更新当前得页面值
        model.addAttribute("pageno", pageno);
        model.addAttribute("count", count);
        return "/book/books";
    }

    //根据条件查询 并进性分页功能
    @RequestMapping("/query/do")
    public String doSelectiveQuery
    (Model model, String bookname, String bookauthor, String bookroom, Integer pageno) {
        List<Book> books = service.querySomeBooks(bookname, bookauthor, bookroom, pageno);
        Integer num = service.querySomeBooksCount(bookname, bookauthor, bookroom, pageno);
        if (num == 0)
            model.addAttribute("mess", "未查到书籍信息");
        int count = 0;
        if (num % 10 == 0)
            count = num / 10;
        else
            count = num / 10 + 1;
        model.addAttribute("count", count);
        model.addAttribute("list", books);
        model.addAttribute("pageno", pageno);
        model.addAttribute("bookname", bookname);
        model.addAttribute("bookauthor", bookauthor);
        model.addAttribute("bookroom", bookroom);
        return "/book/selectbook";
    }

    //添加书籍
    @RequestMapping("/create")
    public String addBook(String name, String author, String price,
                          Integer status, String room, String num, Model model) {
        boolean res = service.addBook(name, author, price, status, room, num);
        model.addAttribute("res", res);
        String mess;
        String html;
        if (res) {
            mess = "添加成功";
            html = "/book/curdhtml/create";
        } else {
            mess = "添加失败";
            html = null;
        }
        model.addAttribute("mess", mess);
        return html;
    }

    //执行删除前的查询操作
    @RequestMapping("/delete/query")
    public String querySelective(String bookname, String bookauthor, String bookroom, Model model) {
        List<Book> someBooks = service.getSomeBooks(bookname, bookauthor, bookroom);
        model.addAttribute("list", someBooks);
        return "/book/curdhtml/delete";
    }

    //执行删除书籍操作
    @RequestMapping("/delete/do")
    public String doDelete(Integer id, Model model) {
        String res = service.doDeleteById(id);
        if (res.equals("right"))
            model.addAttribute("mess", "删除成功");
        else if (res.equals("wrong"))
            model.addAttribute("mess", "删除失败");
        else if(res.equals("borrow"))
            model.addAttribute("mess","删除失败,该书籍还在借记中");     
        return "/book/curdhtml/delete";
    }

    //执行更新书籍信息操作
    @RequestMapping("/update/do")
    public String doUpdate(Model model, Integer id, String name, String author, Integer status, String price, String room, String num) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setRoom(room);
        book.setAuthor(author);
        book.setStatus(status);
        book.setNum(num);
        book.setPrice(price);
        int i = service.updateOnBook(book);
        if (i == 1) {
            model.addAttribute("book", book);
            model.addAttribute("mess", "修改成功");
            model.addAttribute("color", "green");
        } else {
            model.addAttribute("book", book);
            model.addAttribute("mess", "修改失败");
            model.addAttribute("color", "red");
        }
        return "/book/curdhtml/update";
    }

    //执行借书操作
    @RequestMapping("/borrow/do")
    public String doBorrow(Integer id, HttpServletRequest request, Model model,
                           Integer date, String password) {
        User user = (User) request.getSession().getAttribute("user");
        String res = borrowService.borrowOneBook(user, id, date, password);
        //为thymeleaf设置三个默认空值
        model.addAttribute("mess", "");
        model.addAttribute("pswd", "");
        model.addAttribute("book", service.seletOneBookById(id));
        if (res.equals("ok")) {
            model.addAttribute("mess", "借书成功:请在" + DateUtil.nowDateToStr(date) + "当天或之前归还 逾期请联系管理员");
        } else if (res.equals("worng")) {
            model.addAttribute("mess", "借书失败:发生了预期外错误 请联系管理员");
        } else if (res.equals("pswd")) {
            model.addAttribute("pswd", "借书失败:账号或密码错误");
        } else if (res.equals("booknum")) {
            model.addAttribute("mess", "借书失败:书籍库存暂时不足");
        } else if (res.equals("count")) {
            model.addAttribute("mess", "借书失败:已达到最大可借书数目 请先归还其他书后");
        }

        return "/book/borrowbook";
    }

    //借书页面跳转
    @RequestMapping("/borrow")
    public String borrowHtml(Model model, Integer id) {
        Book book = service.seletOneBookById(id);
        model.addAttribute("book", book);
        return "/book/borrowbook";
    }

}
