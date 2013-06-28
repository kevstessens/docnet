<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>EasyRoom.com</title>
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
    <link rel="stylesheet" href="style.css" media="all"/>

<body>

<div id=container class="round">

   <c:import url="header.jsp"/>

    <div id="content">

        <div id="message">

            <c:if test="${error != null}">
                <c:out value="Check username or password."/>
            </c:if>

        </div>

        <form method="post" action="j_security_check">
            <table class="Box">
                <tr>
                    <td>User :</td>
                    <td><input id="j_username" name="j_username" placeholder="Insert user name"></td>
                </tr>

                <tr>
                    <td>Password :</td>
                    <td><input id="j_password" name="j_password" type="password" placeholder="Insert password"></td>
                </tr>

            </table>
            <table>
                <td><input type="submit" class="button" value="Log In"></td>
                <td>
                    <c:if test="${error != null}">
                        <a href="register.jsp" class="button">Register</a>
                    </c:if>
                </td>
            </table>


        </form>

    </div>

    <div id="footer">


        <span> This page its a project for the subject Lab1 at Austral University</span>

    </div>

</div>


</body>
</html>
