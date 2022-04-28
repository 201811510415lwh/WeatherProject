package com.lwh.pojo;

import java.math.BigDecimal;

/**
 * @ClassName WeatherCount
 * @Description TODO
 * @Author Lin
 * @Date 2022/1/6 19:16
 * @Version 1.0
 **/
public class WeatherCount {
    private String year ;
    private BigDecimal temp ;
    private BigDecimal max ;
    private BigDecimal min ;
    private BigDecimal rainfall ;
    private Integer rainyDays ;

    public WeatherCount() {
    }

    public WeatherCount(String year, BigDecimal max, BigDecimal temp, BigDecimal min, BigDecimal rainfall, Integer rainyDays) {
        this.year = year;
        this.max = max;
        this.temp = temp;
        this.min = min;
        this.rainfall = rainfall;
        this.rainyDays = rainyDays;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public BigDecimal getRainfall() {
        return rainfall;
    }

    public void setRainfall(BigDecimal rainfall) {
        //将英寸转化成毫米
        BigDecimal mm;
        mm = new BigDecimal(rainfall.doubleValue()*25.4);
        this.rainfall = mm.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public Integer getRainyDays() {
        return rainyDays;
    }

    public void setRainyDays(Integer rainyDays) {
        this.rainyDays = rainyDays;
    }

    @Override
    public String toString() {
        return "WeatherCount{" +
                "year='" + year + '\'' +
                ", max=" + max +
                ", temp=" + temp +
                ", min=" + min +
                ", rainfall=" + rainfall +
                ", rainyDays=" + rainyDays +
                '}';
    }
}
