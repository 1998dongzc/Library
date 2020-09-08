package com.dzc.springboot.controller;

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

    //跳转学生登录页面
    @RequestMapping("/user/login")
    public String LoginHtml() {
        return "/login/login";
    }

    //跳转管理员登陆页面
    @RequestMapping("/user/rootlogin")
    public String LoginRoot(){
        return "/login/rootlogin";
    }

    //学生登录登陆功能
    @RequestMapping("/user/login/do")
    public String doLogin(Model model, String user, String password) {
        boolean res = loginService.isUser(user, password);
        if (res) {
            System.out.println("登陆成功");
            return "/index/userindex";
        }
        else
            return "/login/login";
    }

    //管理员登陆功能
    @RequestMapping("/user/login/root")
    public String doLoginRoot(){
        return "/index/rootindex.html";
    }

}
