<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2021/12/22
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath",basePath);
%>

<!--写base标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<link rel="stylesheet" href="static/bootstrap3/css/bootstrap.min.css">
<script src="static/script/jquery-3.5.1.js"></script>
<script src="static/bootstrap3/js/bootstrap.min.js"></script>
<script src="/static/script/echarts.js"></script>