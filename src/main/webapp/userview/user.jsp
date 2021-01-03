<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 01.05.2018
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Esteamer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css" type="text/css">
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
    <div>
        <h1>Текущая дата: <%=session.getAttribute("date_now")%></h1>
    </div>
<div>
    <p class="text"> Пользователь: <%=session.getAttribute("name")%><br>
        Магазин: <%=session.getAttribute("magazine")%></p>
</div>
<div>
    <form action="${pageContext.request.contextPath}/userview/searchkart" method="post">
        Номер карты: <input required type="text" id="card" name="card" minlength="1" maxlength="4"
                            onkeypress="return validate(event, this.id)"> <input type="submit" value="Поиск карты">
    </form>
</div>
<div>
    <form action="${pageContext.request.contextPath}/userview/searchtelephone" method="post">
        Поиск по номеру телефона: <input required type="text" name="tel" id="telephone" minlength="12" maxlength="12"> <input type="submit" value="Поиск телефона">
    </form>
</div>

</body>
</html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
<script type="text/javascript">

    jQuery(function($) {

        $.mask.definitions['~']='[+-]';

        $('#date').mask('99.99.9999');

        $('#telephone').mask('375999999999');

    });


    function CheckProcent(value,element_id) {
        if (15<value){
            alert("Процент скидки не может превышать 15%!");
            document.getElementById(element_id).value = "";
        }
    }

    function CheckRadioClient(flag) {
        if (flag){
            document.getElementById('procent').disabled = false;
            document.getElementById('sum').disabled = false;
            document.getElementById('first_sum').disabled = true;
        }
        if(!flag){
            document.getElementById('procent').disabled = true;
            document.getElementById('sum').disabled = true;
            document.getElementById('first_sum').disabled = false;
        }
    }
    function validate(evt, id) {
        // var theEvent = evt || window.event;
        // var key = theEvent.keyCode || theEvent.which;
        // key = String.fromCharCode( key );
        // var regex = /^[0-9]*\.?[0-9]*$/;
        // if( !regex.test(key) ) {
        //     theEvent.returnValue = false;
        //     if(theEvent.preventDefault) theEvent.preventDefault();
        // }
        try{
            var charCode = (evt.which) ? evt.which : event.keyCode;

            if (charCode > 31 && (charCode < 48 || charCode > 57) )
                return false;

        }catch(w){
        }
    }

    function CheckRadio(form) {
        var sms = ch('sms');
        var client = ch('client');
        if (sms && client){
            form.submit();
        } else{
            if (!sms && client) {
                alert("Не выбран пункт SMS!");
                return false;
            }
            if (sms && !client){
                alert("Не выбран пункт КЛИЕНТ!");
                return false;
            }
            if (!sms && !client){
                alert("Не выбраны пункты SMS и КЛИЕНТ!");
                return false;
            }
        }
    }
    function ch(name) {
        var elements = document.getElementsByName(name);
        var flag = false;
        for (var i = 0; i<elements.length; i++){
            if (elements[i].checked){
                flag = true;
            }
        }
        return flag;
    }
</script>