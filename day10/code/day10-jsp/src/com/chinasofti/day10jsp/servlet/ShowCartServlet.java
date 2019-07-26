package com.chinasofti.day10jsp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.day10jsp.beans.Goods;


@WebServlet("/cart/list")
public class ShowCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// 从session中获取当前用户的购物车商品
		HttpSession session = request.getSession();
		// Key代表购买的商品，Value代表购买数量
		Object o = session.getAttribute("myCart");
		
		Map<Goods, Integer> myCart = null;
		if (o != null) {
			myCart = (Map<Goods, Integer>) o;
			// 遍历Map集合
			for (Map.Entry<Goods, Integer> entry : myCart.entrySet()) {
				Goods goods = entry.getKey();
				int total = entry.getValue();
				
				response.getWriter().write("商品名称：" + goods.getGoodsName() + "<br/>");
				response.getWriter().write("价格：" + goods.getPrice() + "<br/>");
				response.getWriter().write("数量 ：" + total + "<br/>");
				response.getWriter().write("<hr/>");
			}
			response.getWriter().write("<a href='" + request.getContextPath() 
					+ "/goods/list'>继续浏览器商品</a>");
		}
	}
	
}
