package com.dzc.springboot.model;

/**
 * @author: 董政辰
 * @date: 2020/9/23 9:30
 * @description:
 * @email：532587041@qq.com
 */
public class MixBorrow {
    private Integer id;
    private String bname;
    private String uname;
    private String broom;
    private String bnum;
    private String stime;
    private String rtime;
    private String status;

    @Override
    public String toString() {
        return "MixBorrow{" +
                "id=" + id +
                ", bname='" + bname + '\'' +
                ", uname='" + uname + '\'' +
                ", broom='" + broom + '\'' +
                ", bnum='" + bnum + '\'' +
                ", stime='" + stime + '\'' +
                ", rtime='" + rtime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBroom() {
        return broom;
    }

    public void setBroom(String broom) {
        this.broom = broom;
    }

    public String getBnum() {
        return bnum;
    }

    public void setBnum(String bnum) {
        this.bnum = bnum;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
