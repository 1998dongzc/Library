package com.dzc.springboot.controller;

import com.dzc.springboot.model.Borrow;
import com.dzc.springboot.model.MixBorrow;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.borrow.BorrowService;
import com.dzc.springboot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/23 8:52
 * @description:
 * @email：532587041@qq.com
 */
@Controller
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @RequestMapping("/display")
    public String borrowPage(Model model, HttpServletRequest request) throws ParseException {
        User user = (User) request.getSession().getAttribute("user");
        List<MixBorrow> borrows = borrowService.getUserBorrows(user.getId());
        model.addAttribute("list",borrows);
        return "/borrow/borrowspage";
    }

}
