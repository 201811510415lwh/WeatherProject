package com.lwh.writable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @ClassName WeatherCountWritable
 * @Description 用于统计天气
 * @Author Lin
 * @Date 2021/12/27 16:22
 * @Version 1.0
 **/
public class WeatherCountWritable implements WritableComparable<WeatherCountWritable> {

    private BigDecimal temp;//气温
    private BigDecimal max;//最高温度
    private BigDecimal min;//最低温度
    private BigDecimal prcp;//降水量
    //标志当天是否发生了（1表示发生了，0表示没有发生）总共有六位数分别为:雾/雨/雪/冰雹/雷/台风
    private Integer rainyDays;

    public WeatherCountWritable() {
    }

    public WeatherCountWritable(BigDecimal temp, BigDecimal max, BigDecimal min, BigDecimal prcp, Integer rainyDays) {
        this.temp = temp;
        this.max = max;
        this.min = min;
        this.prcp = prcp;
        this.rainyDays = rainyDays;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getPrcp() {
        return prcp;
    }

    public void setPrcp(BigDecimal prcp) {
        this.prcp = prcp;
    }

    public Integer getRainyDays() {
        return rainyDays;
    }

    public void setRainyDays(Integer rainyDays) {
        this.rainyDays = rainyDays;
    }

    @Override
    public String toString() {
        return  ""+temp + '\t' + max + '\t' + min + '\t' + prcp + '\t' + rainyDays;
    }

    @Override
    public int compareTo(WeatherCountWritable other) {
        return this.temp.compareTo(other.temp);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(temp.doubleValue());
        out.writeDouble(max.doubleValue());
        out.writeDouble(min.doubleValue());
        out.writeDouble(prcp.doubleValue());
        out.writeInt(rainyDays);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.temp = BigDecimal.valueOf(in.readDouble());
        this.max = BigDecimal.valueOf(in.readDouble());
        this.min = BigDecimal.valueOf(in.readDouble());
        this.prcp = BigDecimal.valueOf(in.readDouble());
        this.rainyDays = Integer.valueOf(in.readInt());
    }
}
