<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2021/12/22
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>历史天气查询</title>

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
                <li class="active "><a href="function/weatherServlet?action=page" class="list-group-item ">查询历史天气</a></li>
                <li><a href="function/weatherServlet?action=forecast">预测未来气温</a></li>
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

<%--查询框--%>
<form action="function/weatherServlet" method="post">
    <input type="hidden" name="action" value="queryWeatherByYearmoda">
    <div class="row">
        <div class="col-md-4 col-md-push-8">
            <div class="input-group">
                <input id="yearmoda" name="yearmoda" value="${param.yearmoda}" type="text" class="form-control" placeholder="请输入要查找的日期">
                <span class="input-group-btn">
                   <button class="glyphicon glyphicon-search btn btn-default" type="submit"></button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-md-4 -->
    </div><!-- /.row -->
</form>

<%--数据展示--%>
<div >
    <table class="table table-hover">
        <thead>
            <tr>
                <th>气象站编号</th>
                <th>时间</th>
                <th>平均气温</th>
                <th>最高气温</th>
                <th>最低气温</th>
                <th>降雨量</th>
                <th>天气状况</th>

            </tr>
        </thead>

        <c:if test="${not empty requestScope.page.items}">
            <c:forEach items="${requestScope.page.items}" var="page">
                <tbody>
                    <tr>
                        <td>${page.stn}</td>
                        <td>${page.yearmoda}</td>
                        <td>${page.temp}℃</td>
                        <td>${page.max}℃</td>
                        <td>${page.min}℃</td>
                        <td>${page.prcp}</td>
                        <td>${page.frshtt}</td>
                    </tr>
                </tbody>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.page.items}">
            <tbody>
                <tr>
                    <td colspan="7" align="center"><a href="function/weatherServlet?action=page">抱歉，暂无有关该日期的数据</a></td>
                </tr>
            </tbody>
        </c:if>

    </table>
</div>


<%--分页--%>
<%@include file="/pages/common/page_nav.jsp" %>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>
