package com.dzc.springboot.service.login;

import com.dzc.springboot.model.User;
import com.dzc.springboot.model.UserAndBooknum;

import java.util.List;
import java.util.Map;

/**
 * @author: 董政辰
 * @date: 2020/9/7 8:44
 * @description:
 * @email：532587041@qq.com
 */
public interface UserService {

    //是否存在学生用户
    public boolean isUser(String user,String password);

    //添加用户 并返回是否成功的布尔值
    public String addUser(String name, String user, String password,String stuid);

    //根据账号获取账号
    public User getUser(String user);

    //是否存在管理员用户
    public boolean isRootUser(String user, String password);

    //更新用户信息
    public int updateInfor(String user, String password);

    //查询所有学生
    public List<User> getAllUser(Integer pageno);

    public List<User> getAllUserById(String stuid);

    public int getCountByStuId(String stuid);

    public boolean delUser(Integer id);

    //获取所有学生并附加上学生借书的数目
    public List<UserAndBooknum> getAllUserAndNum(Integer pageno);
}
