package com.lwh.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName RegressionLineUtils
 * @Description 线性回归
 * @Author Lin
 * @Date 2022/1/15 19:03
 * @Version 1.0
 **/
public class RegressionLineUtils {

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


    public static BigDecimal getTemperature(double[][] array) {

        String tomorrow  = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));

        int year = Integer.parseInt(tomorrow);

        double a1 = getLinePara(array)[0];
        double b = getLinePara(array)[1];
        double temp = a1*year + b;

        return new BigDecimal(temp);

    }

}
