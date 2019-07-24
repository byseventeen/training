package demo04ServetContext对象;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置响应头
		resp.setHeader("Content-Disposition", "attachment;filename=good.jpg");
		// 获取images文件夹在磁盘上路径
		ServletContext servletContext = req.getServletContext();
		/*String imagesPath = servletContext.getRealPath("/images");
		System.out.println("imagesPath = " + imagesPath);
		// 读取图片到输入流中
		FileInputStream fis = new FileInputStream(imagesPath + "/good.jpg");*/
		
		// 把资源文件读取到输入流中
		InputStream inputStream = servletContext.getResourceAsStream("/images/good.jpg");
		
		// 创建字节数组
		byte[] buf = new byte[1024];
//		int len = fis.read(buf);
		int len = inputStream.read(buf);
		while (len != -1) {
			// 把读取到的数据输出到浏览器
			resp.getOutputStream().write(buf, 0, len);
//			len = fis.read(buf);
			len = inputStream.read(buf, 0, len);
		}
		// 关闭输入流
		//fis.close();
		inputStream.close();
	}

}
