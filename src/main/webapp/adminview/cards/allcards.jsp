<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 29.05.2018
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="workdate" scope="page" class="Date.WorkDate"/>
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
        <form action="${pageContext.request.contextPath}/adminview/cards.jsp">
            <input type="submit" value="Вернутся назад" class="submit">
        </form>
    </div>
    <div id="btn3">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
<br>
<label>Поиск по карте</label>
<input type="text" id="search" placeholder="Поиск по картам"><br>
<c:if test="${listsize != null}">
    <form action="${pageContext.request.contextPath}/adminview/listcard" method="post">
        <h3>Диапазон карт</h3>
        <select name="size">
            <c:forEach items="${listsize}" var="list">
                <c:choose>
                    <c:when test="${list == 1}">
                        <option value="${list}">${list} - ${list*100}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${list}">${((list-1)*100)+1} - ${list*100}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <input type="submit" value="Запросить">
    </form>
</c:if>
<br>
<c:choose>
    <c:when test="${list.size()!=0}">
        <table border="1px" class="table1" id="table">
            <tr>
                <td><label class="dline">№ карты</label></td>
                <td><label class="dline">Активность</label></td>
                <td><label class="dline">Процент</label></td>
                <td><label class="dline">Дата выдачи</label></td>
                <td><label class="dline">Магазин</label></td>
                <td><label class="dline">ФИО Клиента</label></td>
                <td><label class="dline">Телефон</label></td>
                <td><label class="dline">Сумма покупок</label></td>
                <td><label class="dline">Сумма бонусов</label></td>
                <td><label class="dline">SMS-рассылка</label></td>
                <td><label class="dline">Функционал</label></td>
            </tr>
            <c:forEach items="${list}" var="cardlist">
                <tr class="tr">
                    <td>${cardlist.number_card}</td>
                    <td><c:if test="${cardlist.valid_card == true}">Активна</c:if>
                        <c:if test="${cardlist.valid_card == false}">Не активна</c:if></td>
                    <td>${cardlist.procent_discont}</td>
                    <td>${workdate.undoParseDate(cardlist.date_create)}</td>
                    <td>${cardlist.magazine}</td>
                    <td>${cardlist.fio_client}</td>
                    <td>${cardlist.telephone}</td>
                    <td>${cardlist.all_sum}</td>
                    <td>${cardlist.cashbek_sum}</td>
                    <td><c:if test="${cardlist.sms == true}">Да</c:if>
                        <c:if test="${cardlist.sms == false}">Нет</c:if></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/adminview/editcard" method="post">
                            <input hidden readonly type="text" name="number_card" value="${cardlist.number_card}">
                            <input type="submit" value="Редактировать">
                        </form>
                        <form action="${pageContext.request.contextPath}/adminview/tranzakt" method="post">
                            <input hidden readonly type="text" name="number_card" value="${cardlist.number_card}">
                            <input hidden readonly type="text" name="listcard" value="${listcard}">
                            <input type="submit" value="История">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
    </c:when>
    <c:otherwise>
        <c:if test="${listcard == 'new'}">
            Нет новых карт на активацию!
        </c:if>
    </c:otherwise>
</c:choose>

</body>
</html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    var $rows = $('#table tr.tr');
    $('#search').keyup(function() {
        var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

        $rows.show().filter(function() {
            var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
            return !~text.indexOf(val);
        }).hide();
    });

</script>