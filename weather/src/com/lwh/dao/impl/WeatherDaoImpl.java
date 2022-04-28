package com.lwh.dao.impl;

import com.lwh.dao.WeatherDao;
import com.lwh.pojo.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName WeatherDaoImpl
 * @Description TODO
 * @Author Lin
 * @Date 2021/12/22 15:32
 * @Version 1.0
 **/
public class WeatherDaoImpl extends BaseDao implements WeatherDao {

    /**
     * @description: 展示所有数据
     * @author LinWeiHeng
     * @date: 2021/12/23-14:46
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    @Override
    public List<Weather> queryAllWeathers() {
        String sql = "select stn,yearmoda,temp,max_temp max,min_temp min ,prcp,frshtt from t_weather";
        return queryForList(Weather.class, sql);
    }

    /**
     * @description: 求总记录数
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @return: java.lang.Integer
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_weather";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    /**
     * @description: 求当前页数据
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @Param begin: 起始位置
     * @Param pageSize: 显示数量
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    @Override
    public List<Weather> queryForPageItems(int begin, int pageSize) {
        String sql = "select stn,yearmoda,temp,max_temp max,min_temp min ,prcp,frshtt from t_weather limit ?,?";

        return queryForList(Weather.class, sql, begin, pageSize);
    }

    /**
     * @description: 搜索日期模块：求按日期查询后的总记录数
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @return: java.lang.Integer
     */
    @Override
    public Integer queryForPageTotalCountByYearmoda(String yearmoda) {
        String sql = "select count(*) from t_weather where yearmoda like '%" + yearmoda + "%'";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();

    }

    /**
     * @description: 搜索日期模块：求按日期查询后的当前页数据
     * @author LinWeiHeng
     * @date: 2021/12/23-16:42
     * @Param begin: 起始位置
     * @Param pageSize: 显示数量
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    @Override
    public List<Weather> queryForPageItemsByYearmoda(int begin, int pageSize, String yearmoda) {
        String sql = "select stn,yearmoda,temp,max_temp max,min_temp min ,prcp,frshtt from t_weather where yearmoda  like '%" + yearmoda + "%' limit ?,?";
        return queryForList(Weather.class, sql, begin, pageSize);
    }



    /**
     * @description: 预测未来一周天气模块：根据日期查询平均气温
     * @author LinWeiHeng
     * @date: 2022/1/4-16:28
     * @Param yearmoda:
     * @Param i:
     * @return: java.math.BigDecimal
     */
    @Override
    public BigDecimal queryAvgTemperatureByYearmoda(String yearmoda, int i) {
        String[] avg = {"AVG(temp)AS temp_avg","AVG(max_temp)AS max_temp_avg","AVG(min_temp)AS min_temp_avg"};
        String str = avg[i];
        String sql = "select "+ str +" from t_weather where yearmoda  like '%" + yearmoda + "' ";
        BigDecimal result = (BigDecimal) queryForSingleValue(sql);
        return result;
    }

    /**
     * @description: 预测未来一周天气模块：查询天气状况
     * @author LinWeiHeng
     * @date: 2022/1/8-14:32
     * @Param yearmoda:
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    @Override
    public List<Weather> queryWeatherConditionsByYearmoda(String yearmoda) {
        String sql = "SELECT frshtt FROM t_weather WHERE yearmoda  LIKE '%" + yearmoda + "' ";
        return queryForList(Weather.class, sql);
    }

    /**
     * @description: 展示所有数据 v1.0 (已弃用)
     * @author LinWeiHeng
     * @date: 2022/1/8-14:33
     * @return: java.util.List<com.lwh.pojo.WeatherCount>
     */
    @Override
    public List<WeatherCount> queryAllWeatherCount() {
        String sql = "SELECT year,temp,max_temp as max,min_temp as min ,rainfall,rainyDays from t_weather_count";
        return queryForList(WeatherCount.class, sql);
    }

    @Override
    public List<BigDecimal> queryAllDaysRainfall() {
        String sql = "SELECT prcp FROM t_weather";
        return queryForList(BigDecimal.class, sql);
    }

    @Override
    public List<String> queryAllYearmoda() {
        return null;
    }

    @Override
    public List<Temperature> queryOneDayWeatherByYearmoda(String yearmoda) {
        String sql = "SELECT max_temp as maxTemperature, temp as avgTemperature ,min_temp as minTemperature" +
                " FROM t_weather WHERE yearmoda  LIKE '%" + yearmoda + "' ";
        return queryForList(Temperature.class, sql);
    }


}
