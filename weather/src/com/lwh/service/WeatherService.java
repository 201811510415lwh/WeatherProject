package com.lwh.service;

import com.lwh.pojo.*;

import java.util.List;

public interface WeatherService {




    /**
     * @description: 展示所有数据
     * @author LinWeiHeng
     * @date: 2021/12/23-14:46
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    List<Weather> queryAllWeathers();

    /**
     * @description: 分页
     * @author LinWeiHeng
     * @date: 2021/12/23-16:36
     * @Param pageNo: 当前页码
     * @Param pageSize: 每页显示的数量
     * @return: com.lwh.pojo.Page<com.lwh.pojo.Weather>
     */
    Page<Weather> page(int pageNo, int pageSize);


    Page<Weather> queryWeatherByYearmoda(int pageNo, int pageSize, String yearmoda);


    List<Forecast> forecastWeeklyWeather();

    List<WeatherCount> queryWeatherCount();

    Rainfalls queryAllDaysRainfall();
}
