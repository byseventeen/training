package demo06Cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getCookie")
public class GetCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取客户端请求中包含的所有Cookie
		Cookie[] cookies = req.getCookies();
		// 遍历Cookie数组
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if ("name".equals(cookieName)) {
				String cookieValue = cookie.getValue();
				System.out.println(cookieName + ":" + cookieValue);
				break;
			}
		}
	}
	
}
