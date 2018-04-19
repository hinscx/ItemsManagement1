<%--
  Created by IntelliJ IDEA.
  User: hins
  Date: 17-12-6
  Time: 上午11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base target="main">
    <title>My JSP 'top.jsp' starting page</title>
</head>
<body style="text-align: center;">

<h1>Item Management System</h1>
<nav>
<a href="<c:url value='/jsp/add.jsp'/>">添加商品</a>
<a href="<c:url value='/ItemServlet?method=findAll'/>">显示所有商品</a>
<a href="<c:url value='/jsp/query.jsp'/>">高级搜索</a>
</nav>
</body>
</html>

