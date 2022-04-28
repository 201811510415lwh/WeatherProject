package com.lwh.web;

import com.lwh.pojo.*;
import com.lwh.service.WeatherService;
import com.lwh.service.impl.WeatherServiceImpl;
import com.lwh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WeatherServlet
 * @Description TODO
 * @Author Lin
 * @Date 2021/12/23 14:49
 * @Version 1.0
 **/
public class WeatherServlet extends BaseServlet{

    private WeatherService weatherService = new WeatherServiceImpl();

    /**
     * @description: 图表展示降雨天数变化情况
     * @author LinWeiHeng
     * @date: 2022/1/16-15:08
     * @Param req:
     * @Param resp:
     * @return: void
     */
    protected void chartsForRainyDays(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用weatherService.forecastWeeklyWeather()方法获取预测好的数据
        List<WeatherCount> weatherCounts = weatherService.queryWeatherCount();
        List<String> date =new ArrayList<String>();
        List<Integer> rainyDays =new ArrayList<Integer>();
        for (WeatherCount weatherCount : weatherCounts) {
            date.add(weatherCount.getYear());
            rainyDays.add(weatherCount.getRainyDays());
        }
        //将预测好的数据存进request域
        req.setAttribute("date",date);
        req.setAttribute("rainyDays",rainyDays);

        //3、请求转发到/pages/function/chart/chartsForRainyDays.jsp页面
        req.getRequestDispatcher("/pages/function/chart/chartsForRainyDays.jsp").forward(req,resp);

    }

    /**
     * @description: 图表展示降雨量变化情况
     * @author LinWeiHeng
     * @date: 2022/1/16-17:08
     * @Param req:
     * @Param resp:
     * @return: void
     */
    protected void chartsForRainy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用weatherService.queryWeatherCount()方法获取数据
        //图表：北京站2000-2021年总降雨量 所需数据
        List<WeatherCount> weatherCounts = weatherService.queryWeatherCount();
        List<String> date =new ArrayList<String>();
        List<Double> rainfall =new ArrayList<Double>();
        for (WeatherCount weatherCount : weatherCounts) {
            date.add(weatherCount.getYear());
            rainfall.add((weatherCount.getRainfall()).doubleValue());
        }

        Rainfalls rainfalls = weatherService.queryAllDaysRainfall();

        //图表：北京站2000-2021年日降雨量 所需数据
        List<Weather> weathers = weatherService.queryAllWeathers();
        List<String> dateAll =new ArrayList<String>();
        List<Double> rainfallAll =new ArrayList<Double>();
        for(Weather weather:weathers) {
            dateAll.add(weather.getYearmoda());
            rainfallAll.add((weather.getPrcp()).doubleValue()*25.4);
        }

        //将预测好的数据存进request域
        req.setAttribute("date",date);
        req.setAttribute("rainfall",rainfall);
        req.setAttribute("dateAll",dateAll);
        req.setAttribute("rainfallAll",rainfallAll);

        //3、请求转发到/pages/function/chart/chartsForRainy.jsp页面
        req.getRequestDispatcher("/pages/function/chart/chartsForRainy.jsp").forward(req,resp);

    }

    /**
     * @description: 图表展示气温变化情况
     * @author LinWeiHeng
     * @date: 2022/1/16-15:08
     * @Param req:
     * @Param resp:
     * @return: void
     */
    protected void chartsForTemp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用weatherService.forecastWeeklyWeather()方法获取预测好的数据
        List<WeatherCount> weatherCounts = weatherService.queryWeatherCount();
        List<String> date =new ArrayList<String>();
        List<Double> maxData =new ArrayList<Double>();
        List<Double> tempData =new ArrayList<Double>();
        List<Double> minData =new ArrayList<Double>();
        for (WeatherCount weatherCount : weatherCounts) {
            date.add(weatherCount.getYear());
            tempData.add((weatherCount.getTemp()).doubleValue());
            maxData.add((weatherCount.getMax()).doubleValue());
            minData.add((weatherCount.getMin()).doubleValue());
        }
        //将预测好的数据存进request域
        req.setAttribute("date",date);
        req.setAttribute("temp",tempData);
        req.setAttribute("max",maxData);
        req.setAttribute("min",minData);

        //3、请求转发到/pages/function/chart/chartsForTemp.jsp页面
        req.getRequestDispatcher("/pages/function/chart/chartsForTemp.jsp").forward(req,resp);

    }

    /**
     * @description: 预测未来一周的天气状况
     * @author LinWeiHeng
     * @date: 2022/1/6-15:08
     * @Param req:
     * @Param resp:
     * @return: void
     */
    protected void forecast(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用weatherService.forecastWeeklyWeather()方法获取预测好的数据
        List<Forecast> forecasts = weatherService.forecastWeeklyWeather();
        List<Double> maxData =new ArrayList<Double>();
        List<Double> tempData =new ArrayList<Double>();
        List<Double> minData =new ArrayList<Double>();
        List<StringBuffer> date =new ArrayList<StringBuffer>();
        List<String> week =new ArrayList<String>();
        for (Forecast forecast : forecasts) {
            maxData.add((forecast.getMax()).doubleValue());
            tempData.add((forecast.getTemp()).doubleValue());
            minData.add((forecast.getMin()).doubleValue());
            date.add(forecast.getTime());
            week.add(forecast.getWeek());
        }

        //将预测好的数据存进request域
        req.setAttribute("forecasts",forecasts);
        req.setAttribute("max",maxData);
        req.setAttribute("temp",tempData);
        req.setAttribute("min",minData);
        req.setAttribute("date",date);
        req.setAttribute("week",week);

        //3、请求转发到/pages/function/forecast.jsp页面
        req.getRequestDispatcher("/pages/function/forecast.jsp").forward(req,resp);


    }

        /**
         * @description: 查询指定时间的天气情况的实现
         * @author LinWeiHeng
         * @date: 2021/12/28-20:35
         * @Param req:
         * @Param resp:
         * @return: void
         */
    protected void queryWeatherByYearmoda(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数pageNo、pageSize、yearmoda
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String yearmoda = req.getParameter("yearmoda");

        //调用weatherService.queryWeatherByYearmoda(yearmoda)方法
        Page<Weather> page = weatherService.queryWeatherByYearmoda(pageNo, pageSize,yearmoda);

        StringBuilder sb = new StringBuilder("function/weatherServlet?action=queryWeatherByYearmoda");
        //如果有查询日期的参数，则追加到分页条的地址参数中
        if (req.getParameter("yearmoda") != null){
            sb.append("&yearmoda=").append(req.getParameter("yearmoda"));
        }

        //设置请求地址
        page.setUrl(sb.toString());
        //保存数据到Request域
        req.setAttribute("page", page);

        //3、请求转发到/pages/function/queryDaysWeather.jsp页面
        req.getRequestDispatcher("/pages/function/queryDaysWeather.jsp").forward(req,resp);

    }

    /**
     * @description: 处理分页功能
     * @author LinWeiHeng
     * @date: 2021/12/6-14:34
     * @Param req:
     * @Param resp:
     * @return: void
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用WeatherService.page(pageNo,pageSize):得到Page对象
        Page<Weather> page = weatherService.page(pageNo, pageSize);

        //设置请求地址
        page.setUrl("function/weatherServlet?action=page");

        //3.保存Page对象到Request域中
        req.setAttribute("page",page);

        //4.请求转发到/pages/function/queryDaysWeather.jsp页面
        req.getRequestDispatcher("/pages/function/queryDaysWeather.jsp").forward(req,resp);
    }

    /**
     * @description: 展示所有数据 ——1.0版本(已弃用)
     * @author LinWeiHeng
     * @date: 2021/12/24-14:53
     * @Param req:
     * @Param resp:
     * @return: void
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过weatherService查询全部天气信息
        List<Weather> weathers = weatherService.queryAllWeathers();
        //2 把全部天气信息保存到Request域中
        req.setAttribute("weathers",weathers );
        //3、请求转发到/pages/function/queryDaysWeather.jsp页面
        req.getRequestDispatcher("/pages/function/queryDaysWeather.jsp").forward(req,resp);
    }

}
