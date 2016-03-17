<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title></title>
    <script type="text/javascript" charset="utf-8" src="js/wordsunk"></script>
    <script type="text/javascript" charset="utf-8" src="resources/js/jquery-2.1.3.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css">
</head>
<body>

<a href="/logout">log out ${user.loginName} [${user.userGroup}]</a>


<br>
-------------------------------------------------
<br>
<table>
    <tr>
        <td>

<span>
    input text<br>
    <textarea rows="7" cols="60" id="textID">"qw  the test the thes these sss err errs </textarea>
</span>
        </td>
        <td>
<span>
    input known words<br>
    <textarea rows="7" cols="60" id="inputKnownWordsID" readonly>${user.words}</textarea>
</span>
        </td>
    </tr>
</table>
<input type="button" value="action" onclick="runWordGeneration()"/>

<div id="workedWordsID">
</div>
<br><br>

<input type="button" value="known words" onclick="getKnownWordsAfterEditing()"/>

<div id="result">----
</div>
<br>
<table>
    <tr>
        <td>
<span>
    output known words<br>
<textarea rows="9" cols="40" id="outputKnownWordsID"></textarea>
</span>
        </td>
        <td>
<span>
    output unKnown words<br>
<textarea rows="9" cols="40" id="outputUnKnownWordsID"></textarea>
</span>
        </td>

        <td>
<span>
    output unKnown words NOT Sorted<br>
<textarea rows="9" cols="40" id="outputUnKnownWordsIDNotSorted"></textarea>
</span>
        </td>
    </tr>
</table>


<div id="rateID">----
</div>
<!--
Test<br>
!!! <span class="knownWord">qwer</span>, <span class="unKnownWord" onclick="clickOnUnkWord(this);">qwer</span>.. s <span>sdvsvd</span>.
-->


</body>
</html>
