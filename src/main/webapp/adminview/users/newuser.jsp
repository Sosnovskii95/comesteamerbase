<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 18.05.2018
  Time: 15:49
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
        <form action="${pageContext.request.contextPath}/adminview/users.jsp">
            <input type="submit" value="Вернутся назад" class="submit">
        </form>
    </div>
    <div id="btn3">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
<div id="conteiner1">
    <div id="conttable">
        <form action="${pageContext.request.contextPath}/adminview/addnewuser" method="post">
            <table class="table1">
                <caption>
                    <h2>
                        Добавление нового пользователя
                    </h2>
                </caption>
                <tr>
                    <td><label class="dline">Логин:</label></td>
                    <td>
                        <input type="text" name="login">
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">Пароль:</label></td>
                    <td>
                        <input type="text" name="password">
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">Название магазина:</label></td>
                    <td>
                        <select name="id_magazine">
                            <c:forEach items="${magazinelist}" var="magazinelist">
                                <option value="${magazinelist.id_magazine}">${magazinelist.name_m}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">ФИО продавца:</label></td>
                    <td>
                        <input type="text" name="fio">
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">Роль пользователя:</label></td>
                    <td>
                        <input type="radio" name="role" value="user" checked>Пользователь
                        <input type="radio" name="role" value="admin">Администратор
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">Статус:</label></td>
                    <td>
                        <input type="radio" name="valid" value="true" checked>Активен
                        <input type="radio" name="valid" value="false">Не активен
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Сохранить" onclick="return confirm('Добавить?')"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

