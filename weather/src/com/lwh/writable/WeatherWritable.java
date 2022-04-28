package com.lwh.writable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @ClassName WeatherWritable
 * @Description 自定义天气数据类型
 * @Author Lin
 * @Date 2021/12/17 19:43
 * @Version 1.0
 **/
public class WeatherWritable implements WritableComparable<WeatherWritable> {

    private String stn;//气象站号
    private String yearmoda;//年月日
    private BigDecimal temp;//气温
    private BigDecimal max;//最高温度
    private BigDecimal min;//最低温度
    private BigDecimal prcp;//降水量
    //标志当天是否发生了（1表示发生了，0表示没有发生）总共有六位数分别为:雾/雨/雪/冰雹/雷/台风
    private String frshtt;

    public WeatherWritable() {
    }

    public WeatherWritable(String stn, String yearmoda, BigDecimal temp, BigDecimal max, BigDecimal min, BigDecimal prcp, String frshtt) {
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

    public String getFrshtt() {
        return frshtt;
    }

    public void setFrshtt(String frshtt) {
        this.frshtt = frshtt;
    }

    @Override
    public String toString() {
        return stn + '\t' + yearmoda + '\t' + temp + '\t' + max + '\t' + min + '\t' + prcp + '\t' + frshtt;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(stn);
        out.writeUTF(yearmoda);
        out.writeDouble(temp.doubleValue());
        out.writeDouble(max.doubleValue());
        out.writeDouble(min.doubleValue());
        out.writeDouble(prcp.doubleValue());
        out.writeUTF(frshtt);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.stn = in.readUTF();
        this.yearmoda = in.readUTF();
        this.temp = BigDecimal.valueOf(in.readDouble());
        this.max = BigDecimal.valueOf(in.readDouble());
        this.min = BigDecimal.valueOf(in.readDouble());
        this.prcp = BigDecimal.valueOf(in.readDouble());
        this.frshtt = in.readUTF();
    }

    @Override
    public int compareTo(WeatherWritable other) {
        return this.yearmoda.compareTo(other.yearmoda);
    }
}
