package com.xdhpx.boot.starter.web.mapper;

import java.util.List;

import com.xdhpx.boot.starter.web.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
     
    @Insert("INSERT INTO user"
    		+ "(id,user_name,password,sex,age,create_time) "
    		+ "VALUES"
    		+ "(#{id},#{userName},#{password},#{sex},#{age},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    User insert(User user);

    @Select("SELECT * FROM user")
    @Results({ @Result(column = "create_time", property = "createTime", javaType = java.util.Date.class) })
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id=#{id}")
    @Results({ @Result(column = "create_time", property = "createTime", javaType = java.util.Date.class) })
    User getById(String id);

    @Update("UPDATE user SET user_name=#{userName} WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int deleteById(String id);
 
}
