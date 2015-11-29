<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title></title>
</head>
<body>
<a href="/logout">log out</a>
<form:errors path="goal.*"/>
goal MVC Spring Start <h2>${msg}</h2>
<%="Hello World!" + (2 * 3 - 1)%>
</body>
</html>
