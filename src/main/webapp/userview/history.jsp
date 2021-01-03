<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 12.08.2018
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="workdate" scope="page" class="Date.WorkDate"/>
<html>
<head>
    <title>Esteamer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/history.css" type="text/css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/logo.png" type="image/png">
</head>
<body>
<div id="conteiner">
    <div id="btn">
        <form action="${pageContext.request.contextPath}/userview/user.jsp" method="post">
            <input type="submit" value="Главное меню" class="submit">
        </form>
    </div>
    <div id="btn1">
        <form action="${pageContext.request.contextPath}/userview/addnewcard.jsp" method="post">
            <input type="submit" value="Новый клиент" class="submit">
        </form>
    </div>
    <div id="btn2">
        <form action="${pageContext.request.contextPath}/userview/history" method="post">
            <div hidden>
                <input type="text" name="dateFrom" value="<%=session.getAttribute("date_now")%>">
                <input type="text" name="dateTo" value="<%=session.getAttribute("date_now")%>">
            </div>
            <input type="submit" value="История операций" class="submit">
        </form>
    </div>
    <div id="btn3">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
<p></p>
<label class="dline">Выбран период ${dateF} - ${dateT}</label>
<p></p>
<form action="${pageContext.request.contextPath}/userview/history" method="post">
    История с <input required type="text" id="dateFrom" name="dateFrom"> до <input required type="text" id="dateTo" name="dateTo"> <input type="submit" value="Запросить">
</form>
<div id="conttable">
    <c:if test="${moneySize!= 0}">
        <details>
            <summary><label class="dline">Оплата денежными средствами</label></summary>
            <p></p>
            <table class="table">
                <tr>
                    <td><label class="dline">Дата покупки</label></td>
                    <td><label class="dline">Время покупки</label></td>
                    <td><label class="dline">Номер карты</label></td>
                    <td><label class="dline">ФИО Клиента</label></td>
                    <td><label class="dline">Сумма покупки</label></td>
                    <td><label class="dline">Сумма начисления</label></td>
                    <td><label class="dline">Способ</label></td>
                </tr>
                <c:forEach var="money" items="${hMoney}">
                    <tr>
                        <td>${workdate.undoParseDate(money.date_buy)}</td>
                        <td>${money.time_buy}</td>
                        <td>${money.number_card}</td>
                        <td>${money.fio_client}</td>
                        <td>${money.buy_sum}</td>
                        <td>${money.cashbek_sum}</td>
                        <td>
                            <c:if test="${money.sposob_oplat == 'nal'}">Наличные</c:if>
                            <c:if test="${money.sposob_oplat == 'beznal'}">Безналичные</c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </details>
    </c:if>
    <p></p>
    <c:if test="${cashbekSize != 0}">
        <details>
            <summary><label class="dline">Оплата денежными средствами и бонусами</label></summary>
            <p></p>
            <table class="table">
                <tr>
                    <td><label class="dline">Дата покупки</label></td>
                    <td><label class="dline">Время покупки</label></td>
                    <td><label class="dline">Номер карты</label></td>
                    <td><label class="dline">ФИО Клиента</label></td>
                    <td><label class="dline">Доплата</label></td>
                    <td><label class="dline">Кешбек</label></td>
                    <td><label class="dline">Что приобретено</label></td>
                    <td><label class="dline">Способ</label></td>
                </tr>
                <c:forEach var="cashbek" items="${hCashbek}">
                    <tr>
                        <td>${workdate.undoParseDate(cashbek.date_buy)}</td>
                        <td>${cashbek.time_buy}</td>
                        <td>${cashbek.number_card}</td>
                        <td>${cashbek.fio_client}</td>
                        <td>${cashbek.buy_sum}</td>
                        <td>${cashbek.cashbek_sum}</td>
                        <td>${cashbek.comment}</td>
                        <td>
                            <c:if test="${cashbek.sposob_oplat == 'nal'}">Наличные</c:if>
                            <c:if test="${cashbek.sposob_oplat == 'beznal'}">Безналичные</c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </details>
    </c:if>
</div>
</body>
</html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
<script type="text/javascript">
    jQuery(function($) {

        $.mask.definitions['~']='[+-]';

        $('#dateFrom, #dateTo').mask('99.99.9999');
    });
</script>