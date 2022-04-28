<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2021/12/28
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>历史天气查询</title>
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


</head>
<body>

<%--  页头  --%>
<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>昼夜温差数据分析</h1>
    <p>机械184林伟恒</p>
</div>

<%--导航条 --%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">主页</a></li>
                <li><a href="function/weatherServlet?action=page" >查询历史天气</a></li>
                <li class="active"><a href="function/weatherServlet?action=forecast" class="list-group-item ">预测未来气温</a></li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        查看图表 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="function/weatherServlet?action=chartsForTemp">查看每年气温变化</a></li>
                        <li><a href="function/weatherServlet?action=chartsForRainy">查看每年降雨量变化</a></li>
                        <li><a href="function/weatherServlet?action=chartsForRainyDays" >查看每年降雨天数变化</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<%--<div class="row">--%>
<%--    <div  class="col-md-6 col-md-push-1" id="chart" style="width: 1000px;height:500px; "></div>--%>
<%--    <script type="text/javascript">--%>
<%--        // 基于准备好的dom，初始化echarts实例--%>
<%--        var myChart = echarts.init(document.getElementById('chart'));--%>

<%--        // 指定图表的配置项和数据--%>
<%--        var option = {--%>
<%--            title: {--%>
<%--                text: "未来一周的天气情况"--%>
<%--            },--%>
<%--            tooltip: {--%>
<%--                trigger: 'axis'--%>
<%--            },--%>
<%--            legend: {},--%>
<%--            toolbox: {--%>
<%--                show: true,--%>
<%--                feature: {--%>
<%--                    dataZoom: {--%>
<%--                        yAxisIndex: 'none'--%>
<%--                    },--%>
<%--                    dataView: { readOnly: false },--%>
<%--                    magicType: { type: ['line', 'bar'] },--%>
<%--                    restore: {},--%>
<%--                    saveAsImage: {}--%>
<%--                }--%>
<%--            },--%>
<%--            xAxis: {--%>
<%--                data:${requestScope.date},--%>
<%--                type: 'category',--%>
<%--                boundaryGap: false,--%>
<%--            },--%>
<%--            yAxis: {--%>
<%--                type: 'value',--%>
<%--                axisLabel: {--%>
<%--                    formatter: '{value} °C'--%>
<%--                }--%>
<%--            },--%>
<%--            series: [--%>
<%--                {--%>
<%--                    name: '最高温度',--%>
<%--                    type: 'line',--%>
<%--                    data: ${requestScope.max},--%>
<%--                    markPoint: {--%>
<%--                        data: [--%>
<%--                            { type: 'max', name: 'Max' },--%>
<%--                            { type: 'min', name: 'Min' }--%>
<%--                        ]--%>
<%--                    },--%>
<%--                    markLine: {--%>
<%--                        data: [{ type: 'average', name: 'Avg' }]--%>
<%--                    }--%>
<%--                },--%>
<%--                {--%>
<%--                    name: '平均温度',--%>
<%--                    type: 'line',--%>
<%--                    data: ${requestScope.temp},--%>
<%--                    markPoint: {--%>
<%--                        data: [--%>
<%--                            { type: 'max', name: 'Max' },--%>
<%--                            { type: 'min', name: 'Min' }--%>
<%--                        ]--%>
<%--                    },--%>
<%--                    markLine: {--%>
<%--                        data: [{ type: 'average', name: 'Avg' }]--%>
<%--                    }--%>
<%--                },--%>
<%--                {--%>
<%--                    name: '最低温度',--%>
<%--                    type: 'line',--%>
<%--                    data: ${requestScope.min},--%>
<%--                    markPoint: {--%>
<%--                        data: [{ name: '周最低', value: -2, xAxis: 1, yAxis: -1.5 }]--%>
<%--                    },--%>
<%--                    markPoint: {--%>
<%--                        data: [--%>
<%--                            { type: 'max', name: 'Max' },--%>
<%--                            { type: 'min', name: 'Min' }--%>
<%--                        ]--%>
<%--                    },--%>
<%--                    markLine: {--%>
<%--                        data: [{ type: 'average', name: 'Avg' }]--%>
<%--                    }--%>
<%--                }--%>
<%--            ]--%>
<%--        };--%>

<%--        // 使用刚指定的配置项和数据显示图表。--%>
<%--        myChart.setOption(option);--%>

<%--    </script>--%>
<%--</div>--%>

<%--表格--%>
<table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <c:forEach items="${requestScope.forecasts}" var="forecast">
                    <th>${forecast.week}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>时间</th>
                <c:forEach items="${requestScope.forecasts}" var="forecast">
                    <td>${forecast.time}</td>
                </c:forEach>
            </tr>

            <tr>
                <th>最高温</th>
                <c:forEach items="${requestScope.forecasts}" var="forecast">
                    <td>${forecast.max}℃</td>
                </c:forEach>
            </tr>
            <tr>
                <th>平均温</th>
                <c:forEach items="${requestScope.forecasts}" var="forecast">
                    <td>${forecast.temp}℃</td>
                </c:forEach>
            </tr>
            <tr>
                <th>最低温</th>
                <c:forEach items="${requestScope.forecasts}" var="forecast">
                    <td>${forecast.min}℃</td>
                </c:forEach>
            </tr>
            <tr>
                <th>降雨概率</th>
                <c:forEach items="${requestScope.forecasts}" var="forecast">
                    <td>${forecast.rainyProbability}%</td>
                </c:forEach>
            </tr>
        </tbody>
</table>




<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>
