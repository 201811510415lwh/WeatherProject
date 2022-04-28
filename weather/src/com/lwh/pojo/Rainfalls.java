package com.lwh.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Rainfalls
 * @Description 日降雨量
 * @Author Lin
 * @Date 2022/1/8 16:49
 * @Version 1.0
 **/
public class Rainfalls {

    private List<String> dates = new ArrayList();
    private List<BigDecimal> rainfall = new ArrayList<>();

    public Rainfalls() {
    }

    public Rainfalls( List<String> dates ,List<BigDecimal> rainfall) {
        this.dates = dates;
        this.rainfall = rainfall;
    }

    public List<BigDecimal> getRainfall() {
        return rainfall;
    }

    public void setRainfall(List<BigDecimal> rainfall) {
        this.rainfall = rainfall;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "时间：" + dates +
                " ,降雨量：" + rainfall ;
    }
}
