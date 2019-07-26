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


@WebServlet("/cart/add")
public class AddCartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取表单的数据
		String goodsId = request.getParameter("goodsId");
		String amount = request.getParameter("amount");
		
		// 从session中获取当前用户的购物车商品
		HttpSession session = request.getSession();
		// Key代表购买的商品，Value代表购买数量
		Object o = session.getAttribute("myCart");
		
		Map<Goods, Integer> myCart = null;
		if (o != null) {
			myCart = (Map<Goods, Integer>) o;
			// 记录map集合中是否存在指定goodsId的商品
			boolean hasGoods = false;
			
			// 遍历Map集合
			for (Map.Entry<Goods, Integer> entry : myCart.entrySet()) {
				Goods goods = entry.getKey();
				int total = entry.getValue();
				// 如果找到goodsId对应的商品，那么就把value（购物车中该商品已有的数量）加上amount（购买数量）
				// 然后再把他们相加的结果重新设置到map中。
				if (goods.getGoodsId() == Integer.parseInt(goodsId)) {
					total += Integer.parseInt(amount);
					myCart.put(goods, total);
					
					hasGoods = true;
				}
			}
			
			if (hasGoods == false) {
				// 把购买商品和数量添加到Map集合中
				Goods goods = getGoods(request, Integer.parseInt(goodsId));
				myCart.put(goods, Integer.parseInt(amount));
			}
		} else {
			// 如果用户还没有添加购物车商品的时候执行下面代码
			myCart = new HashMap<Goods, Integer>();
			// 把购买商品和数量添加到Map集合中
			Goods goods = getGoods(request, Integer.parseInt(goodsId));
			myCart.put(goods, Integer.parseInt(amount));
		}
		
		
		/*System.out.println("-----------购物车的商品-----------");
		for (Map.Entry<Goods, Integer> entry : myCart.entrySet()) {
			System.out.println(entry.getKey().getGoodsId() + " -> " + entry.getValue());
		}*/
		
		// 处理响应中文乱码
		response.setContentType("text/html;charset=utf-8");
		
		// 把Map添加到session对象的myCart属性中。
		session.setAttribute("myCart", myCart);
		response.getWriter().write("商品加入购物车成功！");
		response.getWriter().write("<a href='" + request.getContextPath() 
				+ "/goods/list'>请继续浏览商品</a>");
		response.getWriter().write("<a href='" + request.getContextPath() 
				+ "/cart/list'>浏览购物车</a>");
	}
	
	/**
	 * 根据ID查询商品
	 * @param goodsId
	 * @return
	 */
	private Goods getGoods(HttpServletRequest request, int goodsId) {
		ServletContext sc = request.getServletContext();
		Object o = sc.getAttribute("goods");
		if (o != null) {
			List<Goods> goodsList = (List<Goods>) o;
			// 遍历集合 
			for (Goods goods : goodsList) {
				if (goods.getGoodsId() == goodsId) {
					return goods;
				}
			}
		}
		return null;
	}
}
