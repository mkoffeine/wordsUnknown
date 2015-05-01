<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 05.04.2015
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>User list:</h2>
<table border="1">
  <th>ID</th>
  <th>name</th>
  <th>words</th>
  <th>user group</th>

<c:forEach items="${users}" var="user">
  <tr>
    <td> ${user.id} </td>
    <td> ${user.loginName} </td>
    <td> ${user.words} </td>
    <td> ${user.userGroup} </td>

  </tr>

</c:forEach>


</body>
</html>
