
<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 11.06.2018
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div id="conteiner1">
    <div id="conttable">
        <form action="${pageContext.request.contextPath}/adminview/updatecard" method="post">
            <table name="card" class="table">
                <tr>
                    <td colspan="2"><label class="dline">Информация по карте</label></td>
                </tr>
                <tr hidden>
                    <td><label class="dline">ID карты</label></td>
                    <td><input type="text" name="id_card" value="${card.id_card}" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">Номер карты</label></td>
                    <td><input type="text" name="number_card" value="<%=request.getAttribute("number_card")%>" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">Старые номера</label></td>
                    <td><input type="text" name="old_card" value="${card.old_card}" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">Замена карты</label></td>
                    <td><input type="text" name="new_number"></td>
                </tr>
                <tr>
                    <td><label class="dline">Активность карты</label></td>
                    <td><input type="radio" name="valid_card" value="true" <c:if test="${card.valid_card == true}">checked</c:if>>Да
                        <input type="radio" name="valid_card" value="false" <c:if test="${card.valid_card == false}">checked</c:if>>Нет</td>
                </tr>
                <tr>
                    <td><label class="dline">Дата создания</label></td>
                    <td><input type="text" name="date_create" value="${workdate.undoParseDate(card.date_create)}" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">Магазин выдачи</label></td>
                    <td><input type="text" name="magazine" value="${magazine}" readonly></td>
                </tr>
            </table>
            <br>
            <table name="sum" class="table">
                <tr>
                    <td colspan="2"><label class="dline">Информация по суммам</label></td>
                </tr>
                <tr hidden>
                    <td>Номер суммы</td>
                    <td><input type="hidden" name="id_sum" value="<%=request.getAttribute("id_sum")%>" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">Процент</label></td>
                    <td><input type="text" name="procent" value="${sum.procent_discont}" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">Сумма покупок</label></td>
                    <td><input type="text" name="all_sum" id="all_sum" value="${sum.all_sum}" onkeypress="return validate(event, this.id)"></td>
                </tr>
                <tr>
                    <td><label class="dline">Кешбек</label></td>
                    <td><input type="text" name="cashbek" id="cashbek" value="${sum.cashbek_sum}" onkeypress="return validate(event, this.id)"></td>
                </tr>
                <tr>
                    <td><label class="dline">Доп бонусы</label></td>
                    <td><input type="text" name="gift_sum" id="gift_sum" value="${sum.gift_sum}" onkeypress="return validate(event, this.id)"></td>
                </tr>
            </table>
            <br>
            <table name="client" class="table">
                <tr>
                    <td colspan="2"><label class="dline">Информация о клиенте</label></td>
                </tr>
                <tr hidden>
                    <td>ID клиент</td>
                    <td><input type="hidden" name="id_client" value="<%=request.getAttribute("id_client")%>" readonly></td>
                </tr>
                <tr>
                    <td><label class="dline">ФИО клиента</label></td>
                    <td><input type="text" name="fio_client" value="${client.fio_client}"></td>
                </tr>
                <tr>
                    <td><label class="dline">Дата рождения</label></td>
                    <td><input type="text" name="date_born" id="date" value="${workdate.undoParseDate(client.date_born)}"></td>
                </tr>
                <tr>
                    <td><label class="dline">Телефон</label></td>
                    <td><input type="text" name="telephone" id="phone" value="${client.telephone}"></td>
                </tr>
                <tr>
                    <td><label class="dline">SMS рассылка</label></td>
                    <td>
                        <input type="radio" name="sms" value="true" <c:if test="${client.sms == true}">checked</c:if>> Да
                        <input type="radio" name="sms" value="false" <c:if test="${client.sms == false}">checked</c:if>> Нет
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">Выдача карта</label></td>
                    <td>
                        <input type="radio" name="status_card" value="old" <c:if test="${card.status_card == 'old'}">checked</c:if>>Выдана
                        <input type="radio" name="status_card" value="new" <c:if test="${card.status_card == 'new'}">checked</c:if>>Куплена
                    </td>
                </tr>
                <tr>
                    <td><label class="dline">Статус клиента</label></td>
                    <td>
                        <input type="radio" name="status" value="old" <c:if test="${client.status_client == 'old'}">checked</c:if>> Старый
                        <input type="radio" name="status" value="new" <c:if test="${client.status_client == 'new'}">checked</c:if>> Новый
                    </td>
                </tr>
            </table>
            <p></p>
            <input type="submit" onclick="return confirm('Сохранить изменения?')" value="Сохранить изменения">
        </form>
        <form action="${pageContext.request.contextPath}/adminview/deletecard" method="post">
            <input type="hidden" name="number_card" value="<%=request.getAttribute("number_card")%>">
            <input type="submit" onclick="return confirm('Удалить карту?')" value="Удалить карту">
        </form>
    </div>
</div>
</body>
</html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>

<script type="text/javascript">
    jQuery(function($) {

        $.mask.definitions['~']='[+-]';

        $('#date').mask('99.99.9999');

        $('#phone').mask('375999999999');


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
</script>
