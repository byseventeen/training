package com.heima.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesUtil {
	
	public static String getName(String className) {
		String fileName = "config.properties";
		String name =null;
		try {
			Properties prop = new Properties();
			InputStream in = propertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			prop.load(in);
			name = (String) prop.get(className);
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		return name;
	}

}
