package com.lwh.test;

import com.lwh.pojo.Rainfalls;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName test
 * @Description TODO
 * @Author Lin
 * @Date 2021/12/28 16:39
 * @Version 1.0
 **/
public class test {

    @Test
    public void test4() {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        List<BigDecimal> rainfall = new ArrayList<>();
        rainfall.add(new BigDecimal(50));
        rainfall.add(new BigDecimal(30));
        rainfall.add(new BigDecimal(20));
        Rainfalls rainfalls = new Rainfalls(list, rainfall);
        System.out.println(rainfalls);
    }

    @Test
    public void test3() {
        String str = "20000101";
        System.out.println(str.length());
    }

    @Test
    public void test2() {
        double[] data = new double[] {3.93,2.88,3.40,3.86,4.86,3.30,3.90};
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void test() {
        double a = 12.495654132657487435135;
        BigDecimal b = new BigDecimal(a);
        System.out.println(b);
        System.out.println(b.setScale(2,BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void testForecast(){
        //获取未来一周的日期和星期
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        for (int k = 0; k < 7;k++){
            String tomorrow  = new SimpleDateFormat("MMdd").format(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000) * k));
            Calendar xq = Calendar.getInstance();
            int j = xq.get(Calendar.DAY_OF_WEEK)-1;
            int m = (j+k)%7;

            System.out.println("时间:" + tomorrow + "星期：" +weekDays[m]);
        }
    }
}
