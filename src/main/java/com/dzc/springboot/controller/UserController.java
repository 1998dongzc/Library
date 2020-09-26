package com.dzc.springboot.controller;

import com.dzc.springboot.model.User;
import com.dzc.springboot.service.login.UserService;
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
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    //跳转学生登录页面
    @RequestMapping("/login")
    public String LoginHtml() {
        return "/login/login";
    }

    //跳转管理员登陆页面
    @RequestMapping("/rootlogin")
    public String LoginRoot() {
        return "/login/rootlogin";
    }

    //跳转学生功能首页
    @RequestMapping("/index/stu")
    public String stuIndex(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "/index/userindex";
    }

    //跳转管理员功能首页
    @RequestMapping("/index/root")
    public String rootIndex(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "/index/rootindex";
    }

    //跳转信息修改页面
    @RequestMapping("/modify")
    public String userModifyHtml(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "/infor/userModify";
    }

    //学生登录登陆功能
    @RequestMapping("/login/do")
    public String doLogin(HttpServletRequest request, Model model, String user, String password) {
        boolean res = userService.isUser(user, password);
        User currentUser = userService.getUser(user);
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
    @RequestMapping("/login/root")
    public String doLoginRoot(HttpServletRequest request, Model model, String user, String password) {
        boolean res = userService.isRootUser(user, password);
        if (res) {
            //登录成功 将用户添加到session中
            User currentuser = userService.getUser(user);
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
    @RequestMapping("/modify/do")
    public String ModifyUserDo(HttpServletRequest request,Model model,String username, String password) {
        int i = userService.updateInfor(username, password);
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        if (i>0)
            model.addAttribute("mess","修改账号密码成功");
        else
            model.addAttribute("mess","修改账号密码失败");
        return "/infor/userModify";
    }

    //退出登录功能 学生管理员通用
    @RequestMapping("/login/out")
    public String doLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "/login/login";
    }

}
