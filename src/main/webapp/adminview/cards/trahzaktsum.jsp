<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 27.07.2018
  Time: 17:38
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
        <c:if test="${listcard == 'new'}">
            <form action="${pageContext.request.contextPath}/adminview/listnewcard" method="post">
                <input type="submit" value="Вернутся назад" class="submit">
            </form>
        </c:if>
        <c:if test="${listcard == 'old'}">
            <form action="${pageContext.request.contextPath}/adminview/generatecount" method="post">
                <input type="submit" value="Вернутся назад" class="submit">
            </form>
        </c:if>
    </div>
    <div id="btn3">
        <form action="${pageContext.request.contextPath}/exit" method="post">
            <input type="submit" value="Завершить сеанс" class="submit">
        </form>
    </div>
</div>
<p></p>
<label class="dline">Номер карты: ${number_card}</label>
<p></p>
<label class="dline">Выбран период ${workdate.undoParseDate(dateFrom)} - ${workdate.undoParseDate(dateTo)}</label>
<p></p>
<div id="conteiner2">
    <form action="${pageContext.request.contextPath}/adminview/tranzakt" method="post">
        <input hidden type="text" name="number_card" value="${number_card}" readonly>
        История с <input type="text" name="dateFrom" id="dateFrom" value="${workdate.undoParseDate(dateFrom)}"> по <input type="text" name="dateTo" id="dateTo" value="${workdate.undoParseDate(dateTo)}">
        <input type="submit" value="Запросить">
    </form>
        <div id="conttable">
            <table class="table1">
                <tr>
                    <td><label class="dline">Дата</label></td>
                    <td><label class="dline">Время</label></td>
                    <td><label class="dline">Магазин</label></td>
                    <td><label class="dline">Сумма покупки</label></td>
                    <td><label class="dline">Сумма начисления</label></td>
                    <td><label class="dline">Способ оплаты</label></td>
                    <td><label class="dline">Функции</label></td>
                </tr>
                <c:forEach items="${historyMoney}" var="money">
                    <tr>
                        <td>${workdate.undoParseDate(money.date_buy)}</td>
                        <td>${money.time_buy}</td>
                        <td>${money.magazine}</td>
                        <td>${money.buy_sum}</td>
                        <td>${money.cashbek_sum}</td>
                        <td>
                            <c:if test="${money.sposob_oplat == 'nal'}">Наличные</c:if>
                            <c:if test="${money.sposob_oplat == 'beznal'}">Безналичный</c:if>
                        </td>
                        <td><input type="submit" value="Удалить" onclick="if(confirm('Удалить сумму?')){DeleteSum(${money.id_sale})}"></td>
                    </tr>
                </c:forEach>
            </table>
            <p></p>
            <table class="table1">
                <tr>
                    <td><label class="dline">Дата</label></td>
                    <td><label class="dline">Время</label></td>
                    <td><label class="dline">Магазин</label></td>
                    <td><label class="dline">Сумма покупки</label></td>
                    <td><label class="dline">Бонусы</label></td>
                    <td><label class="dline">Что приобритено</label></td>
                    <td><label class="dline">Способ оплаты</label></td>
                    <td><label class="dline">Функции</label></td>
                </tr>
                <c:forEach items="${historyCashbek}" var="cashbek">
                    <tr>
                        <td>${workdate.undoParseDate(cashbek.date_buy)}</td>
                        <td>${cashbek.time_buy}</td>
                        <td>${cashbek.magazine}</td>
                        <td>${cashbek.buy_sum}</td>
                        <td>${cashbek.cashbek_sum}</td>
                        <td>${cashbek.comment}</td>
                        <td>
                            <c:if test="${cashbek.sposob_oplat == 'nal'}">Наличные</c:if>
                            <c:if test="${cashbek.sposob_oplat == 'beznal'}">Безналичный</c:if>
                        </td>
                        <td><input type="submit" value="Удалить" onclick="DeleteCashbek(${cashbek.id_sale})"></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
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

    function valid(id) {
        var text = document.getElementById(id).value;
        document.getElementById(id).value = text.replace(',','.');
    }

    function validate(evt, id) {
        valid(id);
        try{
            var charCode = (evt.which) ? evt.which : event.keyCode;
            if (charCode==44 || charCode==46){
                valid(id);
                var txt = document.getElementById(id).value;
                if(!(txt.indexOf(".") > -1)){
                    return true;
                }
            }
            if (charCode > 31 && (charCode < 48 || charCode > 57) )
                return false;

        }catch(w){
        }
    }

    <%--function UpdateSum(id_sale) {--%>
        <%--var number_card = ${number_card};--%>
        <%--var dateFrom = document.getElementById("dateFrom").value;--%>
        <%--var dateTo = document.getElementById("dateTo").value;--%>
        <%--var new_sum = document.getElementsByName('new_sum').value;--%>
        <%--var vibor = document.getElementsByName('sposob_oplat');--%>
        <%--var sposob_oplat;--%>
        <%--for (var i = 0; i < vibor.length; i++){--%>
            <%--if (document.getElementsByName('sposob_oplat')[i].checked == true){--%>
                <%--sposob_oplat = document.getElementsByName('sposob_oplat')[i].value;--%>
                <%--break;--%>
            <%--}--%>
        <%--}--%>
        <%--$.ajax({--%>
            <%--type: 'post',--%>
            <%--url:"${pageContext.request.contextPath}/adminview/updatesumtranzakt",--%>
            <%--data: {--%>
                <%--'number_card': number_card,--%>
                <%--'dateF': dateFrom,--%>
                <%--'dateT': dateTo,--%>
                <%--'id_sale': id_sale,--%>
                <%--'new_sum': new_sum,--%>
                <%--'sposob_oplat' : sposob_oplat--%>
            <%--},--%>
            <%--async: true,--%>
            <%--error: function (message) {--%>
                <%--alert(message)--%>
            <%--},--%>
            <%--success: function () {--%>
                <%--location.reload();--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    function DeleteSum(id_sale) {
        var number_card = ${number_card};
        var dateFrom = document.getElementById("dateFrom").value;
        var dateTo = document.getElementById("dateTo").value;
        $.ajax({
            type: 'post',
            url:"${pageContext.request.contextPath}/adminview/deletesumtranzakt",
            data: {
                'number_card': number_card,
                'dateF': dateFrom,
                'dateT': dateTo,
                'id_sale': id_sale
            },
            async: true,
            error: function (message) {
                alert(message)
            },
            success: function () {
                location.reload();
            }
        });
    }

    function DeleteCashbek(id_sale) {
        var number_card = ${number_card};
        var dateFrom = document.getElementById("dateFrom").value;
        var dateTo = document.getElementById("dateTo").value;
        $.ajax({
            type: 'post',
            url:"${pageContext.request.contextPath}/adminview/deletecashbektranzakt",
            data: {
                'number_card': number_card,
                'dateF': dateFrom,
                'dateT': dateTo,
                'id_sale': id_sale
            },
            async: true,
            error: function (message) {
                alert(message)
            },
            success: function () {
                location.reload();
            }
        });
    }
</script>
