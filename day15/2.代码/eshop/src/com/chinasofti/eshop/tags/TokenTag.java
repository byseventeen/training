package com.chinasofti.eshop.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*
	生成token标签的处理类
*/
public class TokenTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// 生成一个Token
		String token = new java.util.Date().getTime() + "";
		// 保存在Session中，并且保存一个表单隐藏域中
		PageContext pageContext = (PageContext) this.getJspContext();
		pageContext.getSession().setAttribute("token", token);
		// 生成隐藏域标签
		this.getJspContext().getOut().write(
				"<input type=\"hidden\" name=\"user_token\" value=\"" + token + "\"/>");
	}

	
	
}
