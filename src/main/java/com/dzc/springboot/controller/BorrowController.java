package com.dzc.springboot.controller;

import com.dzc.springboot.model.MixBorrow;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.borrow.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //管理查看结束情况  并进行分页显示
    @RequestMapping("/display/root")
    public String borrowPageRoot(Model model, Integer pageno) throws ParseException {
        List<MixBorrow> userBorrows = borrowService.getAllBorrows(pageno);
        Integer num = borrowService.getCountBorrow();
        int count=0;
        if(num%10==0)
            count=num/10;
        else
            count=num/10+1;
        model.addAttribute("pageno",pageno);
        model.addAttribute("list",userBorrows);
        model.addAttribute("count",count);
        return "/borrow/rootborrowspage";
    }

    //处理ajax的还书请求功能
    @RequestMapping("/return/do")
    public @ResponseBody boolean doReturn(Integer id){
        boolean res=false;
        res = borrowService.returnBook(id);
        return res;
    }
}
