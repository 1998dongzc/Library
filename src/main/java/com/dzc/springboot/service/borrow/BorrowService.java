package com.dzc.springboot.service.borrow;

import com.dzc.springboot.model.Borrow;
import com.dzc.springboot.model.MixBorrow;
import com.dzc.springboot.model.User;

import java.text.ParseException;
import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/22 15:31
 * @description:
 * @email：532587041@qq.com
 */
public interface BorrowService {

    String borrowOneBook(User user, Integer id, Integer day, String password);

    List<MixBorrow> getUserBorrows(Integer id) throws ParseException;

    List<MixBorrow> getAllBorrows(Integer pageno) throws ParseException;

    Integer getCountBorrow();

    boolean returnBook(Integer id);

    List<Borrow> getBorrows(Integer id);


}
