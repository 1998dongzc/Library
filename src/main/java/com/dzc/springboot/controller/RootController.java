package com.dzc.springboot.controller;

import com.dzc.springboot.dao.UserMapper;
import com.dzc.springboot.model.User;
import com.dzc.springboot.model.UserAndBooknum;
import com.dzc.springboot.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: 董政辰
 * @date: 2020/9/16 8:46
 * @description:
 * @email：532587041@qq.com
 */
@Controller
@RequestMapping("/root")
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    UserMapper mapper;

    //跳转到curd管理页面
    @RequestMapping("/curd")
    public String curdHtml() {
        return "/book/curd";
    }

    //返回管理员功能页面
    @RequestMapping("/index")
    public String indexHtml(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "/index/rootindex";
    }

    //跳转管理学生页面
    @RequestMapping("/stucurd")
    public String userCurdHtml() {
        return "/infor/curd";
    }

    //跳转增加学生页面
    @RequestMapping("/stucurd/add")
    public String addStupage() {
        return "/infor/addstu";
    }

    //增加学生功能
    @RequestMapping("/stucurd/add/do")
    public String addStu(Model model, String name, String user, String pswd, String stuid) {
        String res = userService.addUser(name, user, pswd, stuid);
        if (res.equals("success"))
            model.addAttribute("mess", "添加成功");
        else if (res.equals("isuser"))
            model.addAttribute("mess", "添加失败:账号已存在");
        else model.addAttribute("mess", "添加失败:发生了未知错误 请联系管理员!");
        return "/infor/addstu";
    }

    //跳转删除学生功能
    @RequestMapping("/stucurd/del")
    public String delStuPage(Model model, Integer pageno) {
        List<User> allUser = userService.getAllUser(pageno);
        int count = 0;
        int num = mapper.getAllCount();
        if (num % 10 == 0)
            count = num / 10;
        else
            count = num / 10 + 1;
        model.addAttribute("pageno", pageno);
        model.addAttribute("count", count);
        model.addAttribute("list", allUser);
        return "/infor/delstu";
    }

    //删除页面按学号查询学生
    @RequestMapping("/stucurd/del/query")
    public String queryPagebyStuid(Model model, String stuid) {
        List<User> allUser = userService.getAllUserById(stuid);
        int count = 0;
        int num = userService.getCountByStuId(stuid);
        if (num % 10 == 0)
            count = num / 10;
        else
            count = num / 10 + 1;
        model.addAttribute("list", allUser);
        return "/infor/delquery";
    }

    //删除学生功能
    @RequestMapping("/stucurd/del/do")
    public @ResponseBody boolean doDel(Integer id) {
        boolean res = userService.delUser(id);
        return res;
    }

    //查看所有学生功能
    @RequestMapping("/stucurd/query")
    public String queryAllUser(Model model,Integer pageno){
        int num = mapper.getAllCount();
        int count=0;
        if(num%10==0)
            count=num/10;
        else
            count=num/10+1;
        List<UserAndBooknum> list = userService.getAllUserAndNum(pageno);
        model.addAttribute("list",list);
        model.addAttribute("count",count);
        model.addAttribute("pageno",pageno);
        return "/infor/showstu";
    }
}
