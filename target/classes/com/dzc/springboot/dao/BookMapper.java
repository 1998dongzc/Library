package com.dzc.springboot.dao;

import com.dzc.springboot.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectBooks(@Param("pageno") Integer pageno);

    int selctCount();
    //分页查询书籍信息
    List<Book> selectBookBySelective(@Param("name") String name,
                                     @Param("author") String author,
                                     @Param("room") String room,
                                     @Param("pageno") Integer pageno);
    //同上功能查询总数
    Integer selectBookCountBySelective(@Param("name") String name,
                                     @Param("author") String author,
                                     @Param("room") String room,
                                     @Param("pageno") Integer pageno);

    Book selectOneBook(Book book);

    List<Book> selectBooksnopage(@Param("name") String name,@Param("author") String author,@Param("room") String room);
}