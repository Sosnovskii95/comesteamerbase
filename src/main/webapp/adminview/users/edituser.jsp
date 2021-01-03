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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/logo.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css" type="text/css">
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
    <form action="${pageContext.request.contextPath}/adminview/updateuser" method="post">
        <table class="table1">
            <caption>
                <h2>
                    Редактирование пользователя
                </h2>
            </caption>
            <c:if test="${userlist != null}">
                <input hidden name="id_user" value="<%=request.getParameter("id_user")%>"/>
            </c:if>
            <tr>
                <td><label class="dline">Логин:</label> </td>
                <td>
                    <input type="text" name="login" size="45"
                           value="<c:out value='${userlist.login}' />"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <label class="dline">Пароль:</label>
                </td>
                <td>
                    <input type="text" name="password" size="45"
                           value="">
                </td>
            </tr>
            <tr>
                <td>
                    <label class="dline">Название магазина:</label>
                </td>
                <td>
                    <select name="id_magazine">
                        <c:forEach items="${magazinelist}" var="magazineL">
                            <c:choose>
                                <c:when test="${userlist.magazine == magazineL.id_magazine}">
                                    <option value="${magazineL.id_magazine}" selected>${magazineL.name_m}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${magazineL.id_magazine}">${magazineL.name_m}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="dline">ФИО продавца:</label>
                </td>
                <td>
                    <input type="text" name="fio" size="45"
                           value="<c:out value='${userlist.fio_user}' />"
                    />
                </td>
            </tr>
            <tr>
                <td>
                    <label class="dline">Роль пользователя:</label>
                </td>
                <td>
                    <input type="radio" name="role" value="user"  <c:if test="${userlist.role == 'user'}">checked</c:if>>Пользователь
                    <input type="radio" name="role" value="admin" <c:if test="${userlist.role == 'admin'}">checked</c:if>>Администратор
                </td>
            </tr>
            <tr>
                <td>
                    <label class="dline">Статус:</label>
                </td>
                <td>
                    <input type="radio" name="valid" value="true"  <c:if test="${userlist.valid_user == true}">checked</c:if>>Активен
                    <input type="radio" name="valid" value="false" <c:if test="${userlist.valid_user == false}">checked</c:if>>Не активен
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Сохранить" onclick="return confirm('Сохранить изменения?')"/>
                </td>
            </tr>
        </table>
</form>            
</div>
</div>
</body>
</html>
