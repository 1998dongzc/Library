package com.dzc.springboot.service;

/**
 * @author: 董政辰
 * @date: 2020/9/7 8:44
 * @description:
 * @email：532587041@qq.com
 */
public interface LoginService {

    public boolean isUser(String user,String password);

    public boolean addUser(String name, String user, String password);
}
