<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 17.05.2018
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <table border="1px" id="table" class="table1">
            <tr>
                <td>ID пользователя</td>
                <td>Логин</td>
                <td>Название магазина</td>
                <td>Фамилия</td>
                <td>Роль</td>
                <td>Активность</td>
                <td>Действия</td>
            </tr>
            <c:forEach items="${userlist}" var="cardlist">
                <tr>
                    <td>${cardlist.id_user}</td>
                    <td>${cardlist.login}</td>
                    <c:forEach items="${magazinelist}" var="magazine">
                        <c:if test="${magazine.id_magazine == cardlist.magazine}">
                            <td>${magazine.name_m}</td>
                        </c:if>
                    </c:forEach>
                    <td>${cardlist.fio_user}</td>
                    <td>${cardlist.role}</td>
                    <c:if test="${cardlist.valid_user == true}">
                        <td>Активен</td>
                    </c:if>
                    <c:if test="${cardlist.valid_user == false}">
                        <td>Не активен</td>
                    </c:if>
                    <td>
                        <a href="${pageContext.request.contextPath}/adminview/edituser?id_user=<c:out value="${cardlist.id_user}"/>">Редактировать</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>