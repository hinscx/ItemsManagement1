<%--
  Created by IntelliJ IDEA.
  User: hins
  Date: 17-12-6
  Time: 下午10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<h3 align="center">高级搜索</h3>
<form action="<c:url value="/StaffServlet"/>" method="get">
    <input type="hidden" name="method" value="query">
    <table border="0" align="center" width="40%" style="margin-left: 100px">
        <tr>
            <td width="100px">员工名称</td>
            <td width="40%">
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>员工性别</td>
            <td>
                <select name="gender">
                    <option value="">==请选择性别==</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>手机</td>
            <td>
                <input type="text" name="phone"/>
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email"/>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>

                <input type="submit" value="搜索"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>

    </table>
</form>

</body>
</html>
