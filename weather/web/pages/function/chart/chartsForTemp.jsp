<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2022/1/4
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>查看每年气温变化</title>
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
<%--    <script src="static/script/echarts_4.8.0.js"></script>--%>

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
                <li><a href="function/weatherServlet?action=forecast" >预测未来气温</a></li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        查看图表 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="function/weatherServlet?action=chartsForTemp" class="list-group-item ">查看每年气温变化</a></li>
                        <li><a href="function/weatherServlet?action=chartsForRainy">查看每年降雨量变化</a></li>
                        <li><a href="function/weatherServlet?action=chartsForRainyDays" >查看每年降雨天数变化</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div class="row">
    <div  class="col-md-6 col-md-push-1" id="chart" style="width: 1000px;height:500px; "></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: "北京站2000-2021年气温变化情况"
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {},
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: { readOnly: false },
                    magicType: { type: ['line', 'bar'] },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ${requestScope.date}
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value} °C'
                }
            },
            series: [
                {
                    name: '最高温度',
                    type: 'line',
                    data: ${requestScope.max},
                    markPoint: {
                        data: [
                            { type: 'max', name: 'Max' },
                            { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    }
                },
                {
                    name: '平均温度',
                    type: 'line',
                    data:  ${requestScope.temp},
                    markPoint: {
                        data: [
                            { type: 'max', name: 'Max' },
                            { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    }
                },
                {
                    name: '最低温度',
                    type: 'line',
                    data:  ${requestScope.min},
                    markPoint: {
                        data: [
                            { type: 'max', name: 'Max' },
                            { type: 'min', name: 'Min' }
                        ]
                    },
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    </script>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>
