package com.xay.service.user;

import com.xay.pojo.Role;
import com.xay.pojo.User;

import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);
    //根据用户id修改密码
    boolean updatePwd(int id, String password);

    //查询记录数
    int getUserCount(String userName,int userRole);

    //根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    boolean add(User user);

    boolean deleteUserById(Integer delId);
}
