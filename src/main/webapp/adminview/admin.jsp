<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 01.05.2018
  Time: 17:12
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
        <form action="users.jsp">
            <input type="submit" value="Пользователи" class="submit">
        </form>
    </div>
    <div id="btn2">
        <form action="cards.jsp">
            <input type="submit" value="Карты" class="submit">
        </form>
    </div>
    <div id="btn3">
        <form action="magazine.jsp">
            <input type="submit" value="Магазины" class="submit">
        </form>
    </div>
    <div id="btn4">
        <form action="${pageContext.request.contextPath}/adminview/GenerateNameMagazineServlet" method="post">
            <input type="submit" value="Отчеты" class="submit">
        </form>
    </div>
    <div id="btn5">
        <form action="job.jsp">
            <input type="submit" value="Запуск заданий" class="submit">
        </form>
    </div>
    <div id="btn6">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
</body>
</html>
