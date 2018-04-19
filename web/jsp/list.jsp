<%--
  Created by IntelliJ IDEA.
  User: hins
  Date: 17-12-6
  Time: 下午3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
      <title>员工列表</title>
  </head>
  <body>
    <h3 align="center" >员工列表</h3>
    <table border="1" width="70%" align="center">
        <tr>
            <th>员工姓名</th>
            <th>性别</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
    <c:forEach items="${ pb.itemList }" var="sta">
        <tr>
            <td>${sta.name}</td>
            <td>${sta.gender}</td>
            <td>${sta.phone}</td>
            <td>${sta.email}</td>
            <td>${sta.description}</td>
            <td>
                <a href="<c:url value='/ItemServlet?method=preEdit&id=${sta.id}'/> ">编辑</a>
                <a href="<c:url value='/ItemServlet?method=delete&id=${sta.id}'/> ">删除</a>
            </td>
        </tr>
    </c:forEach>
    </table>
    <br/>
    <br/>

    <center>
        第${pb.pc}页/共${pb.tp}页
        <a href="${pb.url}&pc=1">首页</a>
        <c:if test="${pb.pc>1}">
            <a href="${pb.url}&pc=${pb.pc-1}">上一页</a>
        </c:if>

        <c:choose>
            <c:when test="${pb.tp<=10}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${pb.tp}"/>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${pb.pc-5}"/>
                <c:set var="end" value="${pb.pc+4}"/>
                <%--头溢出--%>
                <c:if test="${begin<1}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="10"/>
                </c:if>
                <%--尾溢出--%>
                <c:if test="${end>pb.tp}">
                    <c:set var="end" value="${pb.tp}"/>
                    <c:set var="begin" value="${pb.tp-9}"/>
                </c:if>
            </c:otherwise>
        </c:choose>

        <%--循环遍历页码列表--%>
        <c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i eq pb.pc}">
                    [${i}]
                </c:when>
                <c:otherwise>
                    <a href="${pb.url}&pc=${i}">[${i}]</a>
                </c:otherwise>
            </c:choose>

        </c:forEach>


        <c:if test="${pb.pc<pb.tp}">
            <a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
        </c:if>
        <a href="${pb.url}&pc=${pb.tp}">尾页</a>

    </center>

  <body/>

<html/>