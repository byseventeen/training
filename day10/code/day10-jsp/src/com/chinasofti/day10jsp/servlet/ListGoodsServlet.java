package com.chinasofti.day10jsp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.day10jsp.beans.Goods;


@WebServlet("/goods/list")
public class ListGoodsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ctrl + shift + o 导入所有包
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList.add(new Goods(110, "小米电视机", "小米电视机", 2699)); 
		goodsList.add(new Goods(220, "小米空调", "小米空调", 1599));
		goodsList.add(new Goods(330, "小米净水器", "小米净水器", 1699));
		goodsList.add(new Goods(440, "小米笔记本", "小米笔记本", 3599));
		goodsList.add(new Goods(550, "小米cc", "小米cc", 1999));
		
		// 把所有商品保存该对象中
		ServletContext sc = request.getServletContext();
		sc.setAttribute("goods", goodsList);
		
		request.getRequestDispatcher("/WEB-INF/jsp/goods/list.jsp")
			.forward(request, response);
	}

}
