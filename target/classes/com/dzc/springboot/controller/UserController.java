package com.dzc.springboot.controller;

import com.dzc.springboot.model.User;
import com.dzc.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 董政辰
 * @date: 2020/9/7 8:34
 * @description:
 * @email：532587041@qq.com
 */
@Controller
public class UserController {

    @Autowired
    private LoginService loginService;

    //跳转登录页面
    @RequestMapping("/user/login")
    public String LoginHtml(){
        return "/login/login";
    }

    //跳转注册页面
    @RequestMapping("/user/register")
    public String RegisterHtml(){
        return "/login/register";
    }

    //登陆功能
    @RequestMapping("/user/login/do")
    public String doLogin(Model model,String user,String password){
        boolean res = loginService.isUser(user, password);
        if(res)
            return "/book/books";
        else
            return "/login/login";
    }

    //注册功能
    @RequestMapping("/user/register/do")
    public String doRegister(Model model,String name,String user,String password){
        //跳转页面的名称
        String htmlName;
        boolean res = loginService.addUser(name,user, password);
        //成功向域中添加 操作信息
        if (res)
            model.addAttribute("mess","注册成功");
        else
            model.addAttribute("mess","注册失败");
        return "/login/login";
    }

}
