package com.xay.dao.role;

import com.xay.dao.BaseDao;
import com.xay.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    //获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Role> roleList = new ArrayList<Role>();
        if(connection!=null){
            String sql = "select * from smbms_role";
            Object[] params = {};
            resultSet = BaseDao.execute(connection,sql,params,resultSet,preparedStatement);
            while (resultSet.next()){
                Role _role = new Role();
                _role.setRoleName(resultSet.getString("roleName"));
                _role.setId(resultSet.getInt("id"));
                _role.setRoleCode(resultSet.getString("roleCode"));
                roleList.add(_role);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return roleList;
    }
}
