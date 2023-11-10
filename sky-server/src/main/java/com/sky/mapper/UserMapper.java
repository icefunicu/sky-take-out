package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /*
    * 微信用户登录
    * */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);
    /*
    *
    * 插入数据
    * */
    void insert(User user);
    @Select("select * from user where id = #{Id}")
    User getById(Long Id);
}
