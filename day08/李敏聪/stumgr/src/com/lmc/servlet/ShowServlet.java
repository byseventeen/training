package com.lmc.servlet;

import com.lmc.dao.UserDao;
import com.lmc.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type","text/html;charset=uft-8");
        UserDao userDao = new UserDao();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<User> users = userDao.list();
            for (User u:users) {
                resp.getWriter().write("姓名："+u.getUsername()+" 年龄："+u.getAge()+
                        " 性别："+u.getGender()+" 生日："+format.format(u.getBirthdate())+"电话："
                +u.getPhone()+" 爱好："+u.getHobby()+"<br/>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
