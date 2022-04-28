package com.lwh.test;

import com.lwh.dao.WeatherDao;
import com.lwh.dao.impl.WeatherDaoImpl;
import com.lwh.pojo.Temperature;
import com.lwh.pojo.Weather;
import com.mysql.cj.protocol.WatchableOutputStream;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class WeatherDaoImplTest {

    private WeatherDao weatherDao = new WeatherDaoImpl();

    @Test
    public void queryOneWeatherByYearmoda() {
        List<Temperature> list = weatherDao.queryOneDayWeatherByYearmoda("0226");
        int size = list.size();
        BigDecimal[] max = new BigDecimal[size];
        BigDecimal[] avg = new BigDecimal[size];
        BigDecimal[] min = new BigDecimal[size];
        for (int i = 0; i < size; i++) {
            max[i] = list.get(i).getMaxTemperature();
            avg[i] = list.get(i).getAvgTemperature();
            min[i] = list.get(i).getMinTemperature();
        }

    }

    @Test
    public void queryAllDaysRainfall() {
        List<BigDecimal> a = weatherDao.queryAllDaysRainfall();
        for (BigDecimal b : a) {
            System.out.println(b);
        }
    }

    @Test
    public void queryAllWeatherCount() {
        System.out.println(weatherDao.queryAllWeatherCount());
    }

    @Test
    public void queryWeatherConditionsByYearmoda() {
        List<Weather> strings = weatherDao.queryWeatherConditionsByYearmoda("0103");
        for (Weather weather : strings) {
            System.out.println(weather.getFrshtt());
        }

    }

    @Test
    public void queryAvgTemperatureByYearmoda() {
        System.out.println(weatherDao.queryAvgTemperatureByYearmoda("0103",2));;
    }

    @Test
    public void queryForPageItemsByYearmoda() {
        List<Weather> weathers = weatherDao.queryForPageItemsByYearmoda(20,10, "0101");
        for (Weather weather : weathers) {
            System.out.println(weather);
        }
    }

    @Test
    public void queryForPageTotalCountByYearmoda() {
        Integer integer = weatherDao.queryForPageTotalCountByYearmoda("0228");
        System.out.println(integer);
    }

    @Test
    public void queryAllWeathers() {
        System.out.println(weatherDao.queryAllWeathers());
    }

    @Test
    public void queryForPageTotalCount(){
        System.out.println(weatherDao.queryForPageTotalCount());
    }
    @Test
    public void queryForPageItems(){
        List<Weather> weathers = weatherDao.queryForPageItems(0, 10);
        for (Weather weather : weathers) {
            System.out.println(weather);
        }
    }
}