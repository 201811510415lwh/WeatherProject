package forecastTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Line
 * @Description 线性回归
 * @Author Lin
 * @Date 2022/1/15 19:03
 * @Version 1.0
 **/
public class Line {

    public static double[] getLinePara(double[][] points) {
        double dbRt[] = new double[2];
        double dbXSum = 0;
        for (int i = 0; i < points[0].length; i++) {
            dbXSum = dbXSum + points[0][i];
        }
        double dbXAvg = dbXSum / points[0].length;
        double dbWHeadVal = 0;
        for (int i = 0; i < points[0].length; i++) {
            dbWHeadVal = dbWHeadVal + (points[0][i] - dbXAvg) * points[1][i];
        }
        double dbWDown = 0;
        double dbWDownP = 0;
        double dbWDownN = 0;
        dbXSum = 0;
        for (int i = 0; i < points[0].length; i++) {
            dbWDownP = dbWDownP + points[0][i] * points[0][i];
            dbXSum = dbXSum + points[0][i];
        }
        dbWDown = dbWDownP - (dbXSum * dbXSum / points[0].length);
        double dbW = dbWHeadVal / dbWDown;
        dbRt[0] = dbW;
        double dbBSum = 0;
        for (int i = 0; i < points[0].length; i++) {
            dbBSum = dbBSum + (points[1][i] - dbW * points[0][i]);
        }
        double dbB = dbBSum / points[0].length;
        dbRt[1] = dbB;
        return dbRt;
    }


    public static void main(String[] args) {
//        double[][] arrPoints = {{2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015}, {0.00,-5,4,2.22,1,4.22,1,6.11,-2,7.5,3.22,-1.22,0.389,2.50,7.00,7.00}};
        double[][] arrPoints = {{2000,2001,2002,2003,2004,2005,2006,
                2007,2008,2009,2010,2011,2012,2013,2014,2015},
                {50,45.3,58.8,48.2,50.9,43.5,36.9,46.9,50,46.4,41,34.2,44.6,51.8,49.1,43.2}};

        String tomorrow  = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));

        int year = Integer.parseInt(tomorrow);
        double temp;
        System.out.println(getLinePara(arrPoints)[0]);
        System.out.println(getLinePara(arrPoints)[1]);
        System.out.println(year);
        double a1 = getLinePara(arrPoints)[0];
        double b = getLinePara(arrPoints)[1];
        temp = a1*year + b;
        System.out.println(temp);

    }

}
