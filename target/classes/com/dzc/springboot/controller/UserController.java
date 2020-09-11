package com.dzc.springboot.controller;

import com.dzc.springboot.model.User;
import com.dzc.springboot.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String LoginRoot() {
        return "/login/rootlogin";
    }

    //跳转信息修改页面
    @RequestMapping("/user/modify")
    public String userModifyHtml(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "/infor/userModify";
    }

    //学生登录登陆功能
    @RequestMapping("/user/login/do")
    public String doLogin(HttpServletRequest request, Model model, String user, String password) {
        boolean res = loginService.isUser(user, password);
        User currentUser = loginService.getUser(user);
        if (res) {
            request.getSession().setAttribute("user", currentUser);
            model.addAttribute("user", currentUser);
            return "/index/userindex";
        } else {
            model.addAttribute("mess", "账号或密码错误");
            return "/login/login";
        }
    }

    //管理员登陆功能
    @RequestMapping("/user/login/root")
    public String doLoginRoot(HttpServletRequest request, Model model, String user, String password) {
        boolean res = loginService.isRootUser(user, password);
        if (res) {
            //登录成功 将用户添加到session中
            User currentuser = loginService.getUser(user);
            request.getSession().setAttribute("user", currentuser);
        }
        //登录错误信息
        else {
            model.addAttribute("mess", "账号或密码错误");
            return "/login/rootlogin";
        }
        return "/index/rootindex";
    }

    //修改学生账号
    @RequestMapping("/user/modify/do")
    public String ModifyUserDo(HttpServletRequest request,Model model,String username, String password) {
        int i = loginService.updateInfor(username, password);
        System.out.println("修改了"+i+"行数据");
        return "/user/login";
    }

    //退出登录功能 学生管理员通用
    @RequestMapping("/user/login/out")
    public String doLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "/login/login";
    }

}
