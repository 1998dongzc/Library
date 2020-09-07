package com.dzc.springboot.service.Impl;

import com.dzc.springboot.dao.UserMapper;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 董政辰
 * @date: 2020/9/7 8:47
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isUser(String user, String password) {
        User res = userMapper.selectByUser(user);
        return password.equals(res.getPassword());
    }

    @Override
    public boolean addUser(String name,String user, String password) {
        //返回结果值 是否成功
        boolean result=false;
        boolean res = isUser(user, password);
        //如果存在用户 则注册失败
        if (res)
            result=false;
        else {
            User u=new User();
            u.setUser(name);
            u.setUser(user);
            u.setPassword(password);
            //如果返回的int值(影响的数据行值)不为1 则插入失败

            int i = userMapper.insert(u);
            if(i==0)
                result=false;
            result=true;
        }
        return result;
    }
}
