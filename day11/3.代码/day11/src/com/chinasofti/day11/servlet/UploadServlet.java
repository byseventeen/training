package com.chinasofti.day11.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 创建DiskFileItemFactory对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建ServletFileUpload对象，该对象可以把Request中的表单数据解析出来
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			// 设置上传文件的大小，如果上传文件超过了10Kb，就会抛出异常。
			servletFileUpload.setFileSizeMax(10240);
			// 调用parseRequest方法解析请求
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);
			// 遍历集合
			for (FileItem fileItem : fileItems) {
				// 如果isFormField返回true，代表是一个普通表单项
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getString("utf-8"));
				} else {
					// 如果isValid返回true，代表是一张图片
					// 如果isValid返回false，那么就不需要执行文件上传
					if (!isValid(fileItem)) {
						request.setAttribute("msg", "只能够上传图片");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
						return; //结束当前方法的执行
					}
					
					/*if (!checkSize(fileItem)) {
						request.setAttribute("msg", "上传图片超过了10Kb！");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
						return; //结束当前方法的执行
					}*/
					
					// 获取上传文件的输入流
					InputStream inputStream = fileItem.getInputStream();
					// 获取上传文件的名字
					String name = fileItem.getName();
					System.out.println("name = " + name);
					// 创建FileOutputStream对象
					String uploadPath = request.getServletContext().getRealPath(
							"/WEB-INF/upload");
					FileOutputStream fos = new FileOutputStream(uploadPath + "/" + name);
					// 读取图片数据，然后写入fos输出流中。
					byte[] buf = new byte[1024];
					int len = inputStream.read(buf);
					while (len != -1) {
						fos.write(buf, 0, len);
						len = inputStream.read(buf);
					}
					// 关闭资源
					fos.close();
				}
			}
		}
		catch (FileSizeLimitExceededException e) {
			request.setAttribute("msg", "上传图片超过了10Kb！");
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
		}
		catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断FileItem文件表单项是否超过了10Kb
	 * @param fi
	 * @return
	 */
	public boolean checkSize(FileItem fi) {
		long size = fi.getSize();
		/*if (size > 1024 * 10) {
			return false;
		}
		return true;*/
		return !(size > 10240);
	}
	
	/**
	 * 判断FileItem文件表单项是否是图片，如果是就返回true，否则返回false。
	 * @param fi
	 * @return
	 */
	public boolean isValid(FileItem fi) {
		String contentType = fi.getContentType(); //例如：image/jpeg
		String regex = "image/[a-zA-Z]{3,4}"; //验证图片的正则
		return contentType.matches(regex);
	}

}
