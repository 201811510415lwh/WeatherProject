package com.lwh.service.impl;

import com.lwh.dao.WeatherDao;
import com.lwh.dao.impl.WeatherDaoImpl;
import com.lwh.pojo.*;
import com.lwh.service.WeatherService;
import com.lwh.utils.RegressionLineUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WeatherServiceImpl
 * @Description TODO
 * @Author Lin
 * @Date 2021/12/22 15:46
 * @Version 1.0
 **/
public class WeatherServiceImpl implements WeatherService {

    private WeatherDao weatherDao = new WeatherDaoImpl();

    /**
     * @description: 搜索日期模块：根据日期查询天气情况
     * @author LinWeiHeng
     * @date: 2021/12/22-16:37
     * @Param yearmoda:
     * @return: com.lwh.pojo.Weather
     */
    @Override
    public Page<Weather> queryWeatherByYearmoda(int pageNo, int pageSize, String yearmoda) {
        Page<Weather> page = new Page<Weather>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = weatherDao.queryForPageTotalCountByYearmoda(yearmoda);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页数
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Weather> items = weatherDao.queryForPageItemsByYearmoda(begin,pageSize,yearmoda);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    /**
     * @description: 预测未来一周的天气状况
     * @author LinWeiHeng
     * @date: 2022/1/8-14:35
     * @return: java.util.List<com.lwh.pojo.Forecast>
     */
    @Override
    public List<Forecast> forecastWeeklyWeather() {
        List<Forecast> forecastList = new ArrayList<Forecast>();

        //获取日期
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        for (int k = 0; k < 7;k++){
            Forecast forecast = new Forecast();
            String tomorrow  = new SimpleDateFormat("MMdd").format(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000) * k));
            Calendar xq = Calendar.getInstance();
            int j = xq.get(Calendar.DAY_OF_WEEK)-1;
            int m = (j+k)%7;
            String week;
            if (k == 0){
                week = "今天";
            }else if (k == 1){
                week = "明天";
            }else {
                week = weekDays[m];
            }
            //求最高、平均、最低温度
            List<Temperature> temperatureList = weatherDao.queryOneDayWeatherByYearmoda(tomorrow);
            int size = temperatureList.size();
            double[] maxArray = new double[size];
            double[] avgArray = new double[size];
            double[] minArray = new double[size];
            for (int i = 0; i < size; i++) {
                maxArray[i] = temperatureList.get(i).getMaxTemperature().doubleValue();
                avgArray[i] = temperatureList.get(i).getAvgTemperature().doubleValue();
                minArray[i] = temperatureList.get(i).getMinTemperature().doubleValue();
            }

            double[] year = {2000,2001,2002,2003,2004,2005,2006,
                    2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021};

            double[][] maxTemp = {year,maxArray};
            double[][] avgTemp = {year,avgArray};
            double[][] minTemp = {year,minArray};

            BigDecimal max = RegressionLineUtils.getTemperature(maxTemp);
            BigDecimal min = RegressionLineUtils.getTemperature(minTemp);
            BigDecimal temp = RegressionLineUtils.getTemperature(avgTemp);


            StringBuffer sb = new StringBuffer(tomorrow);
            sb.insert(2, "/");
            Integer count = weatherDao.queryForPageTotalCountByYearmoda(tomorrow);
            List<Weather> strings = weatherDao.queryWeatherConditionsByYearmoda(tomorrow);
            int i1 = 0;
            for (Weather weather : strings) {
                String frshtt = weather.getFrshtt();
                if ("雨".equals(frshtt)) {
                    i1 ++;
                }
            }
            double rR = i1/count;
            forecast.setTime(new StringBuffer(sb));
            forecast.setWeek(week);
            forecast.setTemp(temp);
            forecast.setMax(max);
            forecast.setMin(min);
            forecast.setRainyProbability(new BigDecimal(rR));
            forecastList.add(forecast);
        }
        return forecastList;
    }


    /**
     * @description: 展示所有数据
     * @author LinWeiHeng
     * @date: 2021/12/23-14:46
     * @return: java.util.List<com.lwh.pojo.Weather>
     */
    @Override
    public List<Weather> queryAllWeathers() {
        return weatherDao.queryAllWeathers();
    }

    /**
     * @description: 分页展示所有数据
     * @author LinWeiHeng
     * @date: 2021/12/23-16:36
     * @Param pageNo: 当前页码
     * @Param pageSize: 每页显示的数量
     * @return: com.lwh.pojo.Page<com.lwh.pojo.Weather>
     */
    @Override
    public Page<Weather> page(int pageNo, int pageSize) {
        Page<Weather> page = new Page<Weather>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = weatherDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页数
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Weather> items = weatherDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public List<WeatherCount> queryWeatherCount() {
        return weatherDao.queryAllWeatherCount();
    }

    /**
     * @description: 图表模块：查询每天的降雨量
     * @author LinWeiHeng
     * @date: 2022/1/8-17:27
     * @return: com.lwh.pojo.Rainfalls
     */
    @Override
    public Rainfalls queryAllDaysRainfall() {
        List<String> list = weatherDao.queryAllYearmoda();
        List<BigDecimal> rainfall = weatherDao.queryAllDaysRainfall();
        Rainfalls rainfalls = new Rainfalls(list, rainfall);
        return rainfalls;
    }
}
