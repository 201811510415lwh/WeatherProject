package com.lwh.pojo;

import java.math.BigDecimal;

/**
 * @ClassName weather
 * @Description 对应数据库的数据
 * @Author Lin
 * @Date 2021/12/22 15:14
 * @Version 1.0
 **/
public class Weather {
    private String stn;//气象站号
    private String yearmoda;//年月日
    private BigDecimal temp;//气温
    private BigDecimal max;//最高温度
    private BigDecimal min;//最低温度
    private BigDecimal prcp;//降水量
    //标志当天是否发生了（1表示发生了，0表示没有发生）总共有六位数分别为:雾/雨/雪/冰雹/雷/台风
    private String frshtt;

    public Weather() {
    }

    public Weather(String stn, String yearmoda, BigDecimal temp, BigDecimal max, BigDecimal min, BigDecimal prcp, String frshtt) {
        this.stn = stn;
        this.yearmoda = yearmoda;
        this.temp = temp;
        this.max = max;
        this.min = min;
        this.prcp = prcp;
        this.frshtt = frshtt;
    }

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    public String getYearmoda() {
        return yearmoda;
    }

    public void setYearmoda(String yearmoda) {
        this.yearmoda = yearmoda;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        BigDecimal dc = temperatureConversion(temp);
        this.temp = dc;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        BigDecimal dc = temperatureConversion(max);
        this.max = dc;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        BigDecimal dc = temperatureConversion(min);
        this.min = dc;
    }

    public BigDecimal getPrcp() {
        return prcp;
    }

    public void setPrcp(BigDecimal prcp) {
        this.prcp = prcp;
    }

    public String getFrshtt() {
        return frshtt;
    }

    /**
     * @description: 将华氏度转换成摄氏度
     * @author LinWeiHeng
     * @date: 2021/12/28-16:28
     * @Param temp:
     * @return: java.math.BigDecimal
     */
    public BigDecimal temperatureConversion(BigDecimal temp){
        BigDecimal dc ;
        dc = new BigDecimal(((temp.doubleValue()-32)/1.8));
        return dc.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @description: 获取天气情况
     * @author LinWeiHeng
     * @date: 2021/12/28-16:29
     * @Param frshtt:
     * @return: void
     */
    public void status(String frshtt) {
        String[] status = new String[]{"雾", "雨", "雪", "冰雹", "雷", "台风"};
        String a = null;
        String b = "";

        for (int i = 0; i < 6; i++) {
            a = frshtt.substring(i, i + 1);
            if (!"0".equals(a)) {
                a = status[i];
                b += a + "、";
            } else {
                a = "";
                b += a;
            }
        }

        if (b.endsWith("、")){
            b = b.substring(0,b.length()-1);
        }

        if (!"".equals(b)) {
            this.frshtt = b;
        } else {
            this.frshtt = "晴";
        }

    }

    public void setFrshtt(String frshtt) {
        status(frshtt);
    }

    @Override
    public String toString() {
        return "weather{" +
                "stn='" + stn + '\'' +
                ", yearmoda='" + yearmoda + '\'' +
                ", temp=" + temp +
                ", max=" + max +
                ", min=" + min +
                ", prcp='" + prcp + '\'' +
                ", frshtt='" + frshtt + '\'' +
                '}';
    }


}
