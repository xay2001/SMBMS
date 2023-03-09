package com.xay.dao.user;

import com.xay.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode) throws SQLException;
}
