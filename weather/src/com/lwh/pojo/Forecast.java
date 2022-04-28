package com.lwh.pojo;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName Forecast
 * @Description 预测未来一周时间内的天气
 * @Author Lin
 * @Date 2021/12/28 20:38
 * @Version 1.0
 **/
public class Forecast {

    private StringBuffer time;
    private String week;
    private BigDecimal temp;
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal rainyProbability;
    // 分页条的请求地址
    private String url;

    public Forecast() {
    }

    public Forecast(StringBuffer time, String week, BigDecimal temp, BigDecimal max, BigDecimal min, BigDecimal rainyProbability, String url) {
        this.time = time;
        this.week = week;
        this.temp = temp;
        this.max = max;
        this.min = min;
        this.rainyProbability = rainyProbability;
        this.url = url;
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

    public StringBuffer getTime() {
        return time;
    }

    public void setTime(StringBuffer time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public BigDecimal getRainyProbability() {
        return rainyProbability;
    }

    public void setRainyProbability(BigDecimal rainyProbability) {
        BigDecimal dc ;
        dc = new BigDecimal((rainyProbability.doubleValue()*100));
        dc.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rainyProbability = dc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "time=" + time +
                ", week='" + week + '\'' +
                ", temp=" + temp +
                ", max=" + max +
                ", min=" + min +
                ", rainyProbability=" + rainyProbability +
                ", url='" + url + '\'' +
                '}';
    }
}
