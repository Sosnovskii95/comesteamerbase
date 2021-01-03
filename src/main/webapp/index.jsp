<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 30.04.2018
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Esteamer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form_invate.css" type="text/css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/logo.png" type="image/png">
  </head>
  <body>
  <form class="form-1" action="${pageContext.request.contextPath}/invate" method="post">
      <p class="field">
        <input required type="text" name="login" placeholder="Логин">
        <i class="icon-user icon-large"><link></i>
      </p>
      <p class="field">
        <input required type="password" name="password" placeholder="Пароль">
        <i class="icon-lock icon-large"></i>
      </p>
    <p class="submit">
      <button type="submit" value="Войти"><i class="icon-arrow-right icon-large"></i></button>
    </p>
  </form>

  </body>
</html>

