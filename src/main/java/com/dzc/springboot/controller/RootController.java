package com.dzc.springboot.controller;

import com.dzc.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 董政辰
 * @date: 2020/9/16 8:46
 * @description:
 * @email：532587041@qq.com
 */
@Controller
@RequestMapping("/root")
public class RootController {

    //跳转到curd管理页面
    @RequestMapping("/curd")
    public String curdHtml(){
        return "/book/curd";
    }

    //返回管理员功能页面
    @RequestMapping("/index")
    public String indexHtml(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "/index/rootindex";
    }
}
