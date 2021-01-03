<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 25.06.2018
  Time: 15:48
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
<form action="${pageContext.request.contextPath}/adminview/admin.jsp">
    <input type="submit" value="Главное меню" class="input">
</form>

<form action="${pageContext.request.contextPath}/exit" method="post">
    <input type="submit" value="Завершить сеанс" class="input">
</form>
<h3>Запуск задания с днями рождения</h3>
<form action="${pageContext.request.contextPath}/DateBornStartStop" method="post">
        <input type="radio" name="radio" value="true">Запустить<br>
        <input type="radio" name="radio" value="false">Остановить<br>
        <input type="submit" value="Сохранить">
</form>
</body>
</html>
