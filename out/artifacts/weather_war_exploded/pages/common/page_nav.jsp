<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2021/12/24
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页--%>
<div class="row">
    <div class="col-md-8 col-md-push-4">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <%--首页、上一页的显示与否--%>
                <c:if test="${requestScope.page.pageNo > 1}">
                    <li>
                        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ requestScope.page.url }&pageNo=1">
                            <span aria-hidden="true">首页</span>
                        </a>
                    </li>
                </c:if>

                <%--页码输出的开始--%>
                <c:choose>
                    <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
                    <c:when test="${ requestScope.page.pageTotal <= 5 }">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>
                    <%--情况2：总页码大于5的情况--%>
                    <c:when test="${requestScope.page.pageTotal > 5}">
                        <c:choose>
                            <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                            <c:when test="${requestScope.page.pageNo <= 3}">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="5"/>
                            </c:when>
                            <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                            <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                                <c:set var="end" value="${requestScope.page.pageTotal}"/>
                            </c:when>
                            <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                            <c:otherwise>
                                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>

                <c:forEach begin="${begin}" end="${end}" var="i">
                    <c:if test="${i == requestScope.page.pageNo}">
                        <li class="active"><a class="disabled">${i}</a></li>
                    </c:if>
                    <c:if test="${i != requestScope.page.pageNo}">
                        <li>
                            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <%--页码输出的结束--%>

                <%--末页、下一页的显示与否--%>
                <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
                    <li>
                        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">
                            <span aria-hidden="true">末页</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${ requestScope.page.pageTotal >1 }">
                    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input" style="width: 40px;text-align: center;"> 页
                    <input class="btn btn-primary btn-sm" type="button" value="确定" id="searchPageBtn">
                </c:if>
                <script type="text/javascript">

                    $(function () {
                        // 跳到指定的页码
                        $("#searchPageBtn").click(function () {

                            var pageNo = $("#pn_input").val();

                            var pageTotal = ${requestScope.page.pageTotal};

                            if (pageNo < 1 || pageNo > pageTotal) {
                                alert("页码输入非法！！")
                            }else {

                                <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                                <%--alert(pageTotal);--%>

                                // javaScript语言中提供了一个location地址栏对象
                                // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                                // href属性可读，可写
                                location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;
                            }
                        });
                    });
                </script>
            </ul>
        </nav>
    </div>
</div>
