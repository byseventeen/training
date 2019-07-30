package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/*
	获取系统的当前时间
*/
public class CurrentTimeTag extends SimpleTagSupport {
	
	// 初始化标签体，只有当标签有标签体的时候才会执行该方法
	@Override
	public void setJspBody(JspFragment jspBody) {
		System.out.println("setJspBody...");
	}

	// 初始化JspContext对象，该对象相当于一个PageContext对象。
	

	// 初始化父标签对象，只有当前标签有父标签的时候才会执行该方法
	@Override
	public void setParent(JspTag parent) {
		System.out.println("setParent...");
	}

	@Override
	public void setJspContext(JspContext pc) {
		System.out.println("setJspContext...");
		super.setJspContext(pc);
	}

	// jsp页面解析标签的时候，会自动执行该标签对应处理类的doTag方法。
	@Override
	public void doTag() throws JspException, IOException {
		// 获取系统时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		String dateStr = sdf.format(d);
		// 把系统时间输出到JSP页面上
		this.getJspContext().getOut().write(dateStr);
	}

	
	
}
