package com.dzc.springboot.service.borrow.Impl;

import com.dzc.springboot.dao.BookMapper;
import com.dzc.springboot.dao.BorrowMapper;
import com.dzc.springboot.dao.UserMapper;
import com.dzc.springboot.model.Book;
import com.dzc.springboot.model.Borrow;
import com.dzc.springboot.model.MixBorrow;
import com.dzc.springboot.model.User;
import com.dzc.springboot.service.borrow.BorrowService;
import com.dzc.springboot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 董政辰
 * @date: 2020/9/22 15:32
 * @description:
 * @email：532587041@qq.com
 */
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    /*
     * 借书成功前的判断事务
     * */
    @Override
    @Transactional
    public String borrowOneBook(User user, Integer id, Integer day, String password) {
        Book book = bookMapper.selectByPrimaryKey(id);
        //获取学生借书总数count
        int count = borrowMapper.borrowCountByUserID(user.getId());
        //先检查借书人的密码是否正确 出错返回pswd提示词 返回错误信息给前端
        if (user.getPassword().equals(password)) {
            //判断书籍数量是否 错误返回booknum提示词 返回错误信息给前端(很少出现此错误
            // 因为数量不够使无法进入此书的借书页面)
            if (count < 3) {
                if (book.getStatus() > 0) {
                    //计算出学生归还书籍的日期
                    String days = String.valueOf(day);
                    String time = DateUtil.nowDateToStr(day);
                    //创建一个borrow
                    Borrow borrow = new Borrow();
                    borrow.setBookid(id);
                    borrow.setUserid(user.getId());
                    borrow.setDate(days);
                    borrow.setReturndate(time);
                    //创建一个book
                    book.setStatus(book.getStatus() - 1);

                    /*
                     * 当借书完成时
                     * borrow表中新增一条记录
                     * 被借书的数量减一
                     * 多库数据更新 所以开启事务
                     * */
                    int res1 = borrowMapper.insert(borrow);
                    int res2 = bookMapper.updateByPrimaryKeySelective(book);
                    //当两个库的数据更新成功 且返回值都为1时为成功 不同时报错wrong信息反馈给前端
                    if (res1 == res2 && res1 == 1)
                        return "ok";
                    else return "wrong";
                } else return "booknum";
            } else return "count";
        } else return "pswd";
    }


    /*
     * 创一个List的MixBorrow(混合bean类)
     * 先从borrow表中查出一个Borrow类的List集合 在遍历list
     * 对数据的修改 并存放于MixBorrow集合中 再反馈给前端
     * */
    @Override
    public List<MixBorrow> getUserBorrows(Integer id) throws ParseException {
        List<Borrow> borrows = borrowMapper.selectAllByUserId(id);
        List<MixBorrow> list = new ArrayList<>();
        for (Borrow one : borrows) {
            MixBorrow t = new MixBorrow();
            //使borrow中的day天数变成借书时间
            String day = one.getDate();
            Integer time = Integer.valueOf(day);
            String starttime = DateUtil.startDateToStr(one.getReturndate(), time);
            //将三个bean的信息结合在一个新的bean中
            //根据borrow的中bookid查出book信息
            User user = userMapper.selectByPrimaryKey(one.getUserid());
            //根据borrow的中userid查出User信息
            Book book = bookMapper.selectByPrimaryKey(one.getBookid());
            t.setUname(user.getName());
            t.setBname(book.getName());
            t.setBnum(book.getNum());
            t.setBroom(book.getRoom());
            //设置归还日期信息
            t.setRtime(one.getReturndate());
            //设置借书的日期信息
            t.setStime(starttime);
            //根据时间工具类的方法 得到判断是否逾期的信息
            t.setStatus(DateUtil.isLate(one.getReturndate()));
            list.add(t);
        }
        return list;
    }

}
