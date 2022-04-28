package com.lwh.dao;

import com.lwh.pojo.Rainfalls;
import com.lwh.pojo.Temperature;
import com.lwh.pojo.Weather;
import com.lwh.pojo.WeatherCount;

import java.math.BigDecimal;
import java.util.List;

public interface WeatherDao {

    /**
     * @description: 展示所有数据
     * @author LinWeiHeng
     * @date: 2021/12/23-14:46
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    List<Weather> queryAllWeathers();

    /**
     * @description: 求总记录数
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @return: java.lang.Integer
     */
    Integer queryForPageTotalCount();

    /**
     * @description: 求当前页数据
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @Param begin: 起始位置
     * @Param pageSize: 显示数量
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    List<Weather> queryForPageItems(int begin, int pageSize);

    /**
     * @description: 求按日期查询后的总记录数
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @return: java.lang.Integer
     */
    Integer queryForPageTotalCountByYearmoda(String yearmoda);

    /**
     * @description: 求按日期查询后的当前页数据
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @Param begin: 起始位置
     * @Param pageSize: 显示数量
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    List<Weather> queryForPageItemsByYearmoda(int begin, int pageSize,String yearmoda);

    /**
     * @description: 根据日期查询平均气温
     * @author LinWeiHeng
     * @date: 2022/1/4-16:28
     * @Param yearmoda:
     * @Param i:
     * @return: java.math.BigDecimal
     */
    BigDecimal queryAvgTemperatureByYearmoda(String yearmoda, int i);

    /**
     * @description: 预测未来一周天气模块：查询天气状况
     * @author LinWeiHeng
     * @date: 2022/1/8-14:32
     * @Param yearmoda:
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    List<Weather> queryWeatherConditionsByYearmoda(String yearmoda);

    List<WeatherCount> queryAllWeatherCount();

    List<BigDecimal> queryAllDaysRainfall();

    List<String> queryAllYearmoda();

    List<Temperature> queryOneDayWeatherByYearmoda(String yearmoda);
}
