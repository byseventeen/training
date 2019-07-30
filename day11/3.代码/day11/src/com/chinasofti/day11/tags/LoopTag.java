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

public class LoopTag extends SimpleTagSupport {
	// 定义成员属性获取标签的count属性值。属性名必须要与标签的属性名相同
	int count;
	
	// 提供setter方法
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("count = " + count);
		// 根据count属性重换输出标签体内容。
		for (int i = 0; i < count; i++) {
			// 输出标签体内容
			this.getJspBody().invoke(null);
		}
	}

	
	
}
