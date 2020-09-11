
package com.dzc.springboot.service.Impl;

import com.dzc.springboot.dao.UserMapper;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    * 登陆事务
    **/
    @Override
    public boolean isUser(String user, String password) {
        User u = userMapper.selectByUser(user);
        if(u==null)
            return false;
        return password.equals(u.getPassword());
    }

    /*
    * 添加用户
    * */
    @Override
    public boolean addUser(String name, String user, String password) {
        boolean res=false;
        User u = userMapper.selectByUser(user);
        //查询是已存在此账号
        if(u!=null)
            res=false;
        else{
            //不存在此账号可以添加
            User one=new User();
            one.setName(name);
            one.setUser(user);
            one.setPassword(password);
            int insert = userMapper.insert(one);
            //影响数据行数不为1则增加失败
            if (insert!=1)
                res=false;
            else res=true;
        }
        return res;
    }

    @Override
    public User getUser(String user) {
        User res = userMapper.selectByUser(user);
        return res;
    }
}
