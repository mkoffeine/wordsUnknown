<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 08.10.2015
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Register</h2>

<form name='registerUser'
      action="/registerUser" method='POST'>

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
            <td>Repeat Password:</td>
            <td><input type='password' name='passwordRepeat'/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type='text' name='email'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"
                                   value="submit"/></td>
        </tr>
    </table>
    <%--
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}" />--%>

</form>

</body>
</html>
