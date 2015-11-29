<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 08.10.2015
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Spring Security Custom Login Form (XML)</h1>

<div id="login-box">

    <h3>Login with Username and Password</h3>
    <a href="register">Register</a>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>
    <c:if test="${not empty registered}">
        <div class="msg">${registered}</div>
    </c:if>

    <form name='loginForm'
          action="<c:url value='/j_spring_security_check' />" method='POST'>

        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit"/></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form>
</div>

</body>
</html>
