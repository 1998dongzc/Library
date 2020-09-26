package com.dzc.springboot.dao;

import com.dzc.springboot.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUser(String user);

    int updateByUser(User user);

    List<User> selectAll(@Param("pageno") Integer pageno);

    int getAllCount();

    List<User> selectAllById(@Param("stuId") String stuid);

    int getAllCountByID(@Param("stuId") String stuid);
}