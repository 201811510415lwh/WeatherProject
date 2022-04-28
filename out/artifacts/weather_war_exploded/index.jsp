<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2021/12/22
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>昼夜温差数据分析</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>昼夜温差数据分析</h1>
    <p>机械184林伟恒</p>
</div>

<%--导航条 --%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active "><a href="index.jsp" class="list-group-item ">主页</a></li>
                <li><a href="function/weatherServlet?action=page" >查询历史天气</a></li>
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


<div class="row">
    <div class="col-md-4">
        <h2>目标实现</h2>
        <div class="fakeimg">
            <a href="#" class="thumbnail">
                <img src="static/image/b.jpg" alt="目标实现">
            </a>
        </div>


        <h3>友情链接</h3>
        <ul class="nav nav-pills nav-stacked">
            <li class="active"><a href="https://blog.sciencenet.cn/blog-219445-865576.html" target="_blank">数据来源</a></li>
            <li><a href="https://blog.csdn.net/long1657/article/details/9316691" target="_blank">数据名称解释</a></li>
            <li><a href="https://weathernew.pae.baidu.com/weathernew/pc?query=%E5%8C%97%E4%BA%AC%E5%A4%A9%E6%B0%94&srcid=4982" target="_blank">北京气温</a></li>
        </ul>
        <hr class="h -->idden-sm hidden-md hidden-lg">
    </div>

    <div class="col-md-8">
        <h2>图表示例：</h2>
        <br>
        <h4>查询每年气温</h4>
        <div class="fakeimg">
            <a href="#" class="thumbnail">
                <img src="static/image/all.png"  style="width:900px;height: 205px">
            </a>
        </div>
        <br>
        <p>从左到右依次是最高、平均、最低气温；降雨天数；年总降雨量的图表示例</p>
        <br>

        <h4>预测未来气温</h4>
        <div class="fakeimg">
            <a href="#" class="thumbnail">
                <img src="static/image/forecats.png"  style="width:900px;height: 205px">
            </a>
        </div>
        <br>
        <p>根据往年气温进行预测</p>

    </div>
</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>

