package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class ShowBodyTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("执行doTag方法...");
		// 输出标签体内容
		this.getJspBody().invoke(null);
		
		// 不输出外面的内容
		// throw new SkipPageException();
	}

}
