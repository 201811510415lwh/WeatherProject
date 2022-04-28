package com.lwh.test;

import com.lwh.pojo.Forecast;
import com.lwh.pojo.Page;
import com.lwh.pojo.Weather;
import com.lwh.pojo.WeatherCount;
import com.lwh.service.WeatherService;
import com.lwh.service.impl.WeatherServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WeatherServiceImplTest {

    private WeatherService weatherService = new WeatherServiceImpl();

    @Test
    public void queryWeatherCount(){
//        System.out.println(weatherService.queryWeatherCount());
        List<WeatherCount> weatherCounts = weatherService.queryWeatherCount();
        List<String> date =new ArrayList<String>();
        List<Double> maxData =new ArrayList<Double>();
        List<Double> tempData =new ArrayList<Double>();
        List<Double> minData =new ArrayList<Double>();
        List<Double> rainfall =new ArrayList<Double>();
        List<Integer> rainyDays =new ArrayList<Integer>();
        for (WeatherCount weatherCount : weatherCounts) {
            date.add(weatherCount.getYear());
            tempData.add((weatherCount.getTemp()).doubleValue());
            maxData.add((weatherCount.getMax()).doubleValue());
            minData.add((weatherCount.getMin()).doubleValue());
            rainfall.add((weatherCount.getRainfall()).doubleValue());
            rainyDays.add(weatherCount.getRainyDays());
        }
        System.out.println(date);
        System.out.println(tempData);
        System.out.println(maxData);
        System.out.println(minData);
        System.out.println(rainfall);
        System.out.println(rainyDays);

    }

    @Test
    public void forecastWeeklyWeather(){
        List<Forecast> forecasts = weatherService.forecastWeeklyWeather();
        List<StringBuffer> date =new ArrayList<StringBuffer>();
        List<Double> maxData =new ArrayList<Double>();
        List<Double> tempData =new ArrayList<Double>();
        List<Double> minData =new ArrayList<Double>();
        for (Forecast forecast : forecasts) {
            date.add(forecast.getTime());
            maxData.add((forecast.getMax()).doubleValue());
            tempData.add((forecast.getTemp()).doubleValue());
            minData.add((forecast.getMin()).doubleValue());
        }
        System.out.println(date);
        System.out.println(maxData);
        System.out.println(tempData);
        System.out.println(minData);
    }

    @Test
    public void queryWeatherByYearmoda(){
        Page<Weather> weatherPage = weatherService.queryWeatherByYearmoda(2, 10, "0101");
        System.out.println(weatherPage);
    }

    @Test
    public void queryAllWeathers() {
        System.out.println(weatherService.queryAllWeathers());
    }

    @Test
    public void page() {
        System.out.println(weatherService.page(1,10));
    }
}