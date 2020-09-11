
package com.dzc.springboot.service.login.Impl;

import com.dzc.springboot.dao.UserMapper;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: 董政辰
 * @date: 2020/9/7 14:56
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    /*
     * 学生登录事务
     **/
    @Override
    public boolean isUser(String user, String password) {
        //根据账号查出是否有次账户
        User u = userMapper.selectByUser(user);
        if (u == null)
            return false;
        //查出用户检查密码与表单密码是否相同
        return (password.equals(u.getPassword()) && !u.getStuId().equals("root"));
    }

    /*
     * 添加用户
     * */
    @Override
    public boolean addUser(String name, String user, String password) {
        boolean res = false;
        User u = userMapper.selectByUser(user);
        //查询是已存在此账号
        if (u != null)
            res = false;
        else {
            //不存在此账号可以添加
            User one = new User();
            one.setName(name);
            one.setUser(user);
            one.setPassword(password);
            int insert = userMapper.insert(one);
            //影响数据行数不为1则增加失败
            if (insert != 1)
                res = false;
            else res = true;
        }
        return res;
    }

    @Override
    public User getUser(String user) {
        User res = userMapper.selectByUser(user);
        return res;
    }

    /*
     * 管理员登录事务
     * */
    @Override
    public boolean isRootUser(String user, String password) {
        User res = userMapper.selectByUser(user);
        //根据账号查出是否有次用户
        if (res == null)
            return false;
        else {
            //如果密码正确 且数据库的学号列为root 则允许登录
            if (res.getPassword().equals(password) && res.getStuId().equals("root"))
                return true;
        }

        return false;
    }

    /*
     *修改密码 开始事务功能
     *  */
    @Override
    @Transactional
    public int updateInfor(String user, String password) {
        User one = new User();
        one.setUser(user);
        one.setPassword(password);
        int i = userMapper.updateByUser(one);
        return i;
    }
}
