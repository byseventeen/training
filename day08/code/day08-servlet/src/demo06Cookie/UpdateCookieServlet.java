package demo06Cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateCookie")
public class UpdateCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建Cookie对象
		Cookie c = new Cookie("name", "mickey");
		// 设置cookie的有效时间
		c.setMaxAge(120);
		// 设置有效路径
		c.setPath("/day08-servlet/getCookie");
		// 把cookie输出到客户端
		resp.addCookie(c);
	}
	
}
