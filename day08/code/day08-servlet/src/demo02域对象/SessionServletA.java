package demo02域对象;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionA")
public class SessionServletA extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		
		// 修改jessionid的有效时间，保证jesessionid不会随着浏览器关闭而消失
		Cookie c = new Cookie("JSESSIONID", session.getId());
		c.setMaxAge(60 * 60 * 24);
		response.addCookie(c);
		
		
		// 往Session中存储数据
		session.setAttribute("name", "aa");
		/*// 如果客户端的cookie被禁用了，那么请求服务器的时候需要加上jsessionid，并且对请求的URL进行加密处理。
		String jsessionId = session.getId();
		String url = response.encodeURL("/day08-servlet/sessionB?JSESSIONID=" + jsessionId);
		response.sendRedirect(url);*/
	}

}
