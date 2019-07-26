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


@WebServlet("/goods/detail")
public class GetGoodsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取商品ID
		String goodsId = request.getParameter("goodsId");
		// 从ServletContext中查询商品的集合
		ServletContext sc = request.getServletContext();
		Object o = sc.getAttribute("goods");
		if (o != null) {
			List<Goods> goodsList = (List<Goods>) o;
			// 遍历集合 
			for (Goods goods : goodsList) {
				if (goods.getGoodsId() == Integer.parseInt(goodsId)) {
					// 把商品发送给detail.jsp显示出来
					request.setAttribute("goods", goods);
					request.getRequestDispatcher("/WEB-INF/jsp/goods/detail.jsp")
						.forward(request, response);
				}
			}
		}
		
	}

}
