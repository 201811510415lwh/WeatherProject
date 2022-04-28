package forecastTest;

import org.apache.commons.io.output.CloseShieldOutputStream;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName test
 * @Description TODO
 * @Author Lin
 * @Date 2022/1/15 22:09
 * @Version 1.0
 **/
public class test {

    @Test
    public void test02(){
        String tomorrow  = new SimpleDateFormat("yy").format(new Date(System.currentTimeMillis()));
        System.out.println(tomorrow);
    }

    @Test
    public void test01() {

        double[] b1 = {10,20,30};
        double[] b2 = {5,6,7};
        double[][] a1 = {b1,b2};

        for (int i = 0; i <b1.length; i++) {
            System.out.println(a1[i][i]);

        }

    }

    @Test
    public void test() {
        double y ;
//        double a1 = 0.32186911764705883;
//        int x = 2017;
//        double a2 = -643.7810661764707;
        double a1 = -0.38205882352941184;
        int x = 2022;
        double a2 = 813.2830882352941;
        y = a1*x + a2 ;
        System.out.println(y);
    }
}

