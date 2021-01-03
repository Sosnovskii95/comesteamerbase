<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 03.07.2019
  Time: 17:37
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
    <div id="btn3">
        <form action="${pageContext.request.contextPath}/adminview/magazine.jsp" method="post">
            <input type="submit" value="Вернуться назад" class="submit">
        </form>
    </div>
    <div id="btn4">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
<div id="conttable">
    <div id="conteiner1">
        <table class="table">
            <tr>
                <td><label class="dline">Название</label> </td>
                <td><label class="dline">Новое название</label> </td>
            </tr>
            <c:forEach items="${mag_list}" var="magazine">
                <tr>
                    <td>${magazine.name_m}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/adminview/editmagazine" method="post">
                            <input type="text" name="id_magazine" value="${magazine.id_magazine}" hidden>
                            <input type="text" name="name_m">
                            <input type="submit" value="Сохранить">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
