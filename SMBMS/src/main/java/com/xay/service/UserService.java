package com.xay.service;

import com.xay.pojo.User;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);
}
