<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 18.06.2018
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
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
<form action="${pageContext.request.contextPath}/adminview/createallcard" method="post">
    <p><strong>Создание отчета по всем картам</strong></p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createlistcardsdate" method="post">
    <p><strong>Создание отчета Активных по смс за период</strong></p>
    <p>Дата от <input type="text" name="dateFrom" required id="dateFrom2"> до
    <input type="text" name="dateTo" required id="dateTo2"></p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createprocentcard" method="post">
    <p><strong>Создание отчета карт по процентам</strong></p>
    <p>Процент от <input type="text" name="procentFrom" size="3" required onkeypress="validate(event)">% до <input required type="text" name="procentBy" size="3" onkeypress="validate(event)">%</p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createdateborncard" method="post">
    <p><strong>Создание отчета по дням рождения</strong></p>
    <p>Дата рождения от <input type="text" name="dateFrom" required id="dateFrom" size="10"> до <input type="text" required name="dateBy" id="dateBy" size="10"></p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createvalidcard" method="post">
    <p><strong>Создание отчета по активности карт</strong></p>
    <p><input type="radio" name="valid" value="true" checked> Активна
        <input type="radio" name="valid" value="false"> Не активна</p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createstatusclient" method="post">
    <p><strong>Создание отчета по статусу клиента</strong></p>
    <p><input type="radio" name="status" value="old" checked> Старый клиент
        <input type="radio" name="status" value="new"> Новый клиент</p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createsmscard" method="post">
    <p><strong>Создание отчета согласных на sms рассылку</strong></p>
    <p><input type="radio" name="sms" value="true" checked> Согласен
        <input type="radio" name="sms" value="false"> Не согласен</p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createbuycard" method="post">
    <p><strong>Создание отчета выданных карт</strong></p>
    <p><input type="radio" name="status_card" value="new" checked> Купленные
        <input type="radio" name="status_card" value="old"> Выданные</p>
    <input type="submit" value="Создать отчет">
</form>
<form action="${pageContext.request.contextPath}/adminview/createoldcard" method="post">
    <p><strong>Создание отчета замененных карт</strong></p>
    <input type="submit" value="Создать отчет">
</form>
<div>
    <form action="${pageContext.request.contextPath}/adminview/createbuyproduct" method="post">
        <p><strong>Создание отчета проданных за кешбек товаров</strong></p>
        <p>Дата с <input type="text" name="dateFrom" id="dateFrom1" required size="10" value="<%=request.getAttribute("dateStart")%>"> по <input type="text" name="dateBy" required id="dateBy1" size="10" value="<%=request.getAttribute("dateNow")%>">
            <select name="magazine">
                <option value="Все">Все магазины</option>
                <c:forEach items="${list}" var="listmagazine">
                    <option value="${listmagazine.name_m}">${listmagazine.name_m}</option>
                </c:forEach>
            </select></p>
        <input type="submit" value="Создать отчет">
    </form>
</div>
</body>
</html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
<script type="text/javascript">
    jQuery(function($) {

        $.mask.definitions['~']='[+-]';

        $('#dateFrom, #dateFrom1, #dateFrom2').mask('99.99.9999');

        $('#dateBy, #dateBy1, #dateTo2').mask('99.99.9999');

        $('#phone').mask('375999999999');


    });

    function validate(evt) {
        var theEvent = evt || window.event;
        var key = theEvent.keyCode || theEvent.which;
        key = String.fromCharCode( key );
        var regex = /[0-9]|\.,/;
        if( !regex.test(key) ) {
            theEvent.returnValue = false;
            if(theEvent.preventDefault) theEvent.preventDefault();
        }
    }
</script>
