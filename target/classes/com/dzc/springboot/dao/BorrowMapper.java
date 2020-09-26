package com.dzc.springboot.dao;

import com.dzc.springboot.model.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    List<Borrow> selectAllByUserId(Integer id);

    int borrowCountByUserID(@Param("userid") Integer id);

    List<Borrow> selectAllBorrow(@Param("pageno") Integer pageno);

    int getBorrowCount();
}