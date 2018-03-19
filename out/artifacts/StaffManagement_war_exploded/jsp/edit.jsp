<%--
  Created by IntelliJ IDEA.
  User: hins
  Date: 17-12-7
  Time: 上午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">编辑员工</h3>
<form action="<c:url value='/StaffServlet'/>" method="post" >
    <input type="hidden" name="method" value="edit"/>
    <input type="hidden" name="id" value="${staff.id}"/>
    <table border="0" align="center" width="40%" style="margin-left: 100px">
        <tr>
            <td width="100px">员工名称</td>
            <td width="40%">
                <input type="text" name="name" value="${staff.name}"/>
            </td>
            <td align="left">
                <label id="nameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>员工性别</td>
            <td>
                <input type="radio" name="gender" value="男" id="male" <c:if test="${staff.gender =='男'}">checked="checked"</c:if>>
                <label for="male">男</label>
                <input type="radio" name="gender" value="女" id="female" <c:if test="${staff.gender == '女'}">checked="checked"</c:if>>
                <label for="female">女</label>
            </td>
            <td>
                <label id="genderError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>手机</td>
            <td>
                <input type="text" name="phone" id="phone" value="${staff.phone}"/>
            </td>
            <td>
                <label id="phoneError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" id="email" value="${staff.email}"/>
            </td>
            <td>
                <label id="emailError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>描述</td>
            <td>
                <textarea rows="5" cols="30" name="description">${staff.description}</textarea>
            </td>
            <td>
                <label id="discriptionError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="submit" value="编辑员工"/>
                <input type="reset" name="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>


</body>
</html>