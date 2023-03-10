package com.xay.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.xay.pojo.User;
import com.xay.service.UserService;
import com.xay.service.UserServiceImpl;
import com.xay.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

//实现Servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method != null) {
            this.updatePwd(req, resp);
        } else if (method.equals("checkoldpwd") && method != null) {
            this.checkOldPwd(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        //从Seesion里面获取ID;
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        System.out.println("UserServlet:" + newpassword);

        boolean flag = false;

        if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {
            UserServiceImpl userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) o).getId(), newpassword);

            System.out.println(flag);
            System.out.println(((User) o).getId() + "+" + newpassword);

            if (flag) {
                req.setAttribute("message", "修改密码成功，请退出，使用新密码登录");
                //密码修改成功，移除当前Session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.setAttribute("message", "密码修改失败");
            }
        } else {
            req.setAttribute("message", "新密码有问题");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //验证旧密码,Session中有用户的密码
    public void checkOldPwd(HttpServletRequest req, HttpServletResponse resp) {
        //从Seesion里面获取ID;
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map：结果集
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (o == null) {//Session失效了，Session过期了
            //Session过期了
            resultMap.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            //旧密码输入为空
            resultMap.put("result", "error");
        } else {
            String userPassword = ((User) o).getUserPassword();//Session中用户的密码
            if (oldpassword.equals(userPassword)) {//密码正确
                resultMap.put("result", "true");
            } else {//密码错误
                resultMap.put("result", "false");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //JSONArray 阿里巴巴的JSON工具类，转换格式
            /*
            * resultMap = ["result","sessionerror","result","error"]
            * JSON格式 = {key:value}
            * */
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
