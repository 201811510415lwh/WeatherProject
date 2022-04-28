package com.lwh.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName Temperature
 * @Description TODO
 * @Author Lin
 * @Date 2022/2/26 16:45
 * @Version 1.0
 **/
public class Temperature {
    private BigDecimal maxTemperature;
    private BigDecimal avgTemperature;
    private BigDecimal minTemperature;

    public Temperature() {
    }

    public Temperature(BigDecimal maxTemperature, BigDecimal avgTemperature, BigDecimal minTemperature) {
        this.maxTemperature = maxTemperature;
        this.avgTemperature = avgTemperature;
        this.minTemperature = minTemperature;
    }

    public BigDecimal getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(BigDecimal maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public BigDecimal getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(BigDecimal avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public BigDecimal getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(BigDecimal minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Override
    public String toString() {
        return  "["+ maxTemperature +","+ avgTemperature + "," + minTemperature + "]";
    }
}
