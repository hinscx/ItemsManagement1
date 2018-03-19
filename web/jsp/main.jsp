<%--
  Created by IntelliJ IDEA.
  User: hins
  Date: 17-12-6
  Time: 上午11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>主页</title>
</head>
<frameset rows="20%,*">
    <frame src="<c:url value='/jsp/top.jsp'/>" name="top"/>
    <frame src="<c:url value='/jsp/welcome.jsp'/>" name="main"/>
</frameset>
</html>
