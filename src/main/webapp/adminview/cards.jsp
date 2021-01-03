<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 24.05.2018
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Esteamer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css" type="text/css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/logo.png" type="image/png">
</head>
<body>
<div id="conteiner">
    <div id="btn1">
        <form action="${pageContext.request.contextPath}/adminview/admin.jsp">
            <input type="submit" value="Главное меню" class="submit">
        </form>
    </div>
    <div id="btn2">
        <form action="${pageContext.request.contextPath}/adminview/cards/newcard.jsp" method="post">
            <input type="submit" value="Добавить карту" class="submit">
        </form>
    </div>
    <div id="btn3">
        <form action="${pageContext.request.contextPath}/adminview/generatecount">
            <input type="submit" value="Все карты" class="submit">
        </form>
    </div>
    <div id="btn4">
        <form action="${pageContext.request.contextPath}/adminview/listnewcard" method="post">
            <input type="submit" value="Новые карты" class="submit">
        </form>
    </div>
    <div id="btn5">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
</body>
</html>
