<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 26.06.2018
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<p></p>
<div id="conttable">
    <form action="${pageContext.request.contextPath}/userview/addnewcard" method="post" name="new_client" onsubmit="CheckRadio(this);return false;">
        <table border="1px" class="table">
            <tr><td align="center" colspan="2"><label class="dline">Добавление нового клиента</label></td></tr>
            <tr>
                <td><label class="dline">№ карты</label></td>
                <td><input required type="text" class="text1" name="number_card" id="number_card" placeholder="123"
                           minlength="1" onkeypress="return ValidateNumber(event, this.id)" onchange="CheckCard(this.value);"></td>
            </tr>
            <tr>
                <td><label class="dline">Дата выдачи</label></td>
                <td><%=session.getAttribute("date_now")%></td>
            </tr>
            <tr>
                <td><label class="dline">Место получения</label></td>
                <td><%=session.getAttribute("magazine")%></td>
            </tr>
            <tr>
                <td><label class="dline">Фамилия</label></td>
                <td><input required type="text" class="text1" placeholder="Иванов" name="familia" minlength="3"></td>
            </tr>
            <tr>
                <td><label class="dline">Имя</label></td>
                <td><input required type="text" class="text1" placeholder="Иван" name="imia" minlength="2"></td>
            </tr>
            <tr>
                <td><label class="dline">Отчество</label></td>
                <td><input required type="text" class="text1" placeholder="Иванович" name="otchestvo" minlength="5"></td>
            </tr>
            <tr>
                <td><label class="dline">Дата рождения</label></td>
                <td><input required type="text" class="text1" placeholder="11-11-1970" name="date_born" id="date" minlength="10" onchange="ValidDate(this.value)"></td>
            </tr>
            <tr>
                <td><label class="dline">№ телефона</label></td>
                <td><input required type="text" class="text1" placeholder="375291231231" name="telephone" id="phone" min="12"></td>
            </tr>
            <tr>
                <td><label class="dline">Выдача карты</label></td>
                <td><input type="radio" name="status_card" value="old">Выдана
                    <input type="radio" name="status_card" value="new">Куплена
                </td>
            </tr>
            <tr>
                <td><label class="dline">SMS рассылка</label></td>
                <td><input name="sms" type="radio" value="true">Да
                    <input name="sms" type="radio" value="false">Нет</td>
            </tr>
            <tr>
                <td colspan="2" align="center"><label class="dline">Статус клиента</label></td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="client" value="old" onclick="CheckRadioClient(true)"><label class="dline">Старый клиент</label><br>
                </td>
                <td>
                    <div><input required type="text" class="text1" name="sum" id="sum" placeholder="Сумма накоплений" minlength="2" onkeypress="return ValidateNumber(event, this.id)" disabled></div>
                </td>
            </tr>
            <tr>
                <td><input type="radio" name="client" value="new" onclick="CheckRadioClient(false)"><label class="dline">Новый клиент</label></td>
                <td><input required type="text" class="text1" id="first_sum" placeholder="Сумма первой покупки" name="first_sum" minlength="1" onkeypress="return ValidateNumber(event, this.id)" disabled></td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <input type="submit" name="sendData" id="sendData" value="Добавить клиента">
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="topLayer" style="display:none;">
    <h1>Ваш запрос обрабатывается. Пожалуйста, подождите...</h1>
</div>
</body>
</html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>
<script type="text/javascript">

    $().ajaxStop($.unblockUI);

    // $(document).ready(
    //     function () {
    //         $('#sendData').click(
    //             function() {
    //                 $.blockUI({message: $('#topLayer'), css: { 'font-size': '0.7em' }});
    //                 //$('#result').load('check.php', {'name': $('#nameField').val()});
    //             }
    //         );
    //     }
    // );

    function click(){
        $.blockUI({message: $('#topLayer'), css: { 'font-size': '0.7em' }});
    }


    jQuery(function($) {

        $.mask.definitions['~']='[+-]';


        $('#date').mask('99-99-9999');

        $('#phone').mask('375999999999');


    });

    function ValidDate(value) {
        var dd = parseInt(String(value.substring(0,2)));
        var mm = parseInt(String(value.substring(3,5)));
        if (31<dd || 12<mm){
            document.getElementById("date").value = "";
        }
    }

    function valid(id) {
        var text = document.getElementById(id).value;
        document.getElementById(id).value = text.replace(',','.');
    }

    function ValidateNumber(event, id) {
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

    function CheckRadioClient(flag) {
        if (flag) {
            //document.getElementById('procent').disabled = false;
            document.getElementById('sum').disabled = false;
            document.getElementById('first_sum').disabled = true;
        }
        if (!flag) {
            //document.getElementById('procent').disabled = true;
            document.getElementById('sum').disabled = true;
            document.getElementById('first_sum').disabled = false;
        }
    }
    function CheckRadio(form) {
        var sms = ch('sms');
        var client = ch('client');
        var status_card = ch('status_card');
        var str = String("Проверьте:");
        if (sms && client && status_card){
            form.submit();
            click();
        } else{
            if (!sms){
                str += " SMS";
            }
            if (!client){
                str += " КЛИЕНТ";
            }
            if (!status_card) {
                str += " СТАТУС КАРТЫ";
            }
            if (str.valueOf() != String("Проверьте:").valueOf()){
                str += "!";
                alert(str);
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
    function CheckCard(value) {
        var xhr = new XMLHttpRequest();
        var body = 'number_card='+encodeURIComponent(value);
        xhr.open("POST",'${pageContext.request.contextPath}/userview/checkcard', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(body);
        xhr.onreadystatechange = function () {
            if (xhr.readyState !=4) return;

            if (xhr.responseText == 1){
                alert("Карта с №"+value+" существует!")
                document.getElementById("number_card").value = "";
            }
        }
    }
</script>