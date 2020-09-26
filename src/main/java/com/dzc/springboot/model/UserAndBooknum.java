package com.dzc.springboot.model;

/**
 * @author: 董政辰
 * @date: 2020/9/26 15:32
 * @description:
 * @email：532587041@qq.com
 */
public class UserAndBooknum {

    private Integer id;

    private String name;

    private String user;

    private String password;

    private String stuId;

    private Integer booknum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Integer getBooknum() {
        return booknum;
    }

    public void setBooknum(Integer booknum) {
        this.booknum = booknum;
    }
}
