package demo01静态导入;

import static java.lang.System.out;
import static java.lang.Math.*;
//import static demo01静态导入.Math.*;

public class Demo01 {

	public static void main(String[] args) {
		out.println("aa");
		out.println("aa");
		out.println("aa");
		out.println("aa");
		out.println("aa");
		out.println("aa");
		
		abs(-10);
		
		demo01静态导入.Math.abs(-10);
		random();
		acos(10.0);
	}

}

