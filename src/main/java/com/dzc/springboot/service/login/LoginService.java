package com.dzc.springboot.service.login;

import com.dzc.springboot.model.User;

/**
 * @author: 董政辰
 * @date: 2020/9/7 8:44
 * @description:
 * @email：532587041@qq.com
 */
public interface LoginService {

    //是否存在学生用户
    public boolean isUser(String user,String password);

    //添加用户 并返回是否成功的布尔值
    public boolean addUser(String name, String user, String password);

    //根据账号获取账号
    public User getUser(String user);

    //是否存在管理员用户
    public boolean isRootUser(String user, String password);

    public int updateInfor(String user, String password);
}
