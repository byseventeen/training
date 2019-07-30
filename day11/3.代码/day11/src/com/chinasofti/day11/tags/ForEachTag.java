package com.chinasofti.day11.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/*
	 实现foreach标签的功能
*/
public class ForEachTag extends SimpleTagSupport {
	Object items;
	String var;
	
	public void setItems(Object items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Class对象的isArray方法用于判断该Class是否是一个数组类型，如果是则返回true，否则返回false
		if (items.getClass().isArray()) {
			// 如果items是数组类型，那么就把items强制转换Object[]数组
			Object[] arr = (Object[]) items;
			// 遍历数组，然后把数组元素设置到pageContext对象中
			for (Object o : arr) {
				// 把数组中每一个元素添加pageContext的属性中，属性名就是var指定。
				this.getJspContext().setAttribute(var, o);
				// 输出标签体内容
				this.getJspBody().invoke(null);
			}
		}
		
		// 遍历Collection（List或Set）、
		else if (items instanceof Collection) {
			// Collection是所有单列集合的父接口
			Collection c = (Collection) items;
			// 遍历Collection
			Iterator itr = c.iterator();
			// itr.hasNext()判断当前指针是否有指向元素，如果有就返回true，否则返回false
			// itr.next()获取迭代器指针指向的数据，并且指针向下移动一个单位
			while (itr.hasNext()) {
				Object o = itr.next();
				this.getJspContext().setAttribute(var, o);
				this.getJspBody().invoke(null);
			}
		}
		
		// 遍历Map
		else if (items instanceof Map) {
			Map<Object, Object> itemsMap = (Map<Object, Object>) items;
			for (Map.Entry<Object, Object> entry : itemsMap.entrySet()) {
				this.getJspContext().setAttribute(var, entry);
				this.getJspBody().invoke(null);
			}
		}
		
	}

}
