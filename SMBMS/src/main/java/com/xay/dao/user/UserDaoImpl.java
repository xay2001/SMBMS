package com.xay.dao.user;

import com.mysql.cj.util.StringUtils;
import com.xay.dao.BaseDao;
import com.xay.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(Connection connection, String userCode,String userPassword) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        if (connection != null) {
            String sql = "SELECT * FROM smbms_user WHERE userCode=? AND userPassword = ?";
            Object[] params = {userCode,userPassword};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, preparedStatement);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            //关闭
            BaseDao.closeResource(null, preparedStatement, resultSet);
        }
        return user;
    }

    //修改当前用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection != null) {
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object params[] = {password,id};
            execute = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null, preparedStatement, null);
        }
        return execute;
    }
    //根据用户名或者角色查询用户总数
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        if(connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            ArrayList<Object> list = new ArrayList<Object>();//存放我们的参数
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" AND u.userName like ?");
                list.add("'%"+userName+"%'");
            }
            if(userRole>0){ //where trim
                sql.append(" AND u.userRole = ?");
                list.add(userRole);//index:1
            }
            //list转换为数组
            Object[] params = list.toArray();

            System.out.println("UserDaoImpl->getUserCount："+sql.toString());//输出完整的SQL语句
            resultSet = BaseDao.execute(connection,sql.toString(),params,resultSet,preparedStatement);
            if(resultSet.next()){
                count = resultSet.getInt("count");//从结果集中获取数量
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        if(connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u ,smbms_role r where u.userRole = r.id");
            List<Object> list  = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("'%"+userName+"%'");
            }
            if(userRole>0){
                sql.append(" and u.userRole=?");
                list.add(userRole);
            }
            //在数据库中分页使用limit startIndex,pageSize; 开始的索引=（当前的页码-1）*每页显示的条数
            //0,5  1  0  01234
            //5,5  2  5  56789
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql ----- >"+sql.toString());
            resultSet = BaseDao.execute(connection,sql.toString(),params,resultSet,preparedStatement);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
                userList.add(user);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return userList;
    }
}
