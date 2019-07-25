package com.lmc.servlet;

import com.lmc.dao.UserDao;
import com.lmc.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistServlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type","text/html;charset=uft-8");
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setGender(req.getParameter("gender"));
        user.setAge( Integer.valueOf(req.getParameter("age")));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(req.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthdate(date);
        user.setPhone(req.getParameter("phone"));
        user.setHobby(req.getParameter("hobby"));
        UserDao userDao = new UserDao();
        try {
            userDao.addOne(user);
            resp.getWriter().write("录入学生成功！<a href='/lmc/stuList'>【查看所有学生信息】</a>");
//            System.out.println(user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(user.toString());
    }
}
