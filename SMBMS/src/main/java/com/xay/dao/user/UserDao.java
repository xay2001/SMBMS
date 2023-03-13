package com.xay.dao.user;

import com.xay.pojo.Role;
import com.xay.pojo.User;
import com.xay.util.Constants;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode,String userPassword) throws SQLException;
    //修改当前用户密码
    public int updatePwd(Connection connection,int id,String password) throws SQLException;
    //查询用户总数
    public int getUserCount(Connection connection,String userName,int userRole) throws SQLException;
    //获取用户列表 通过条件查询
    public List<User> getUserList(Connection connection,String userName ,int userRole,int currentPageNo,int pageSize) throws SQLException;
}
