package live;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class demo1 {
	public static void main(String[] args) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日");
        Scanner in = new Scanner(System.in);
        System.out.println("请依次输入您的出生年月日：");
        int year = in.nextInt();
        int mouth = in.nextInt();
        int day = in.nextInt();
        Date nowDate = new Date(2019,07,18);
        Date oldDate = new Date(year,mouth,day);
        String now = s.format(nowDate);
        String old = s.format(oldDate);
        long nowTime = nowDate.getTime();
        long oldTime = oldDate.getTime();
        //System.out.println(now);
//      System.out.println(nowTime);
//      System.out.println(oldTime);
        long time = nowTime-oldTime;
        System.out.printf("你已经来到这个星球%s天了~",time/ 1000 / 60 / 60 / 24);
        
	}
}
