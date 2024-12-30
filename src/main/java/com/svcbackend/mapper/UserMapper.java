package com.svcbackend.mapper;

import com.svcbackend.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserModel loginUser(@Param("username") String username);
    void registerUser(UserModel user);
}
