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
