package com.dzc.springboot.dao;

import com.dzc.springboot.model.Borrow;

public interface BorrowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);
}