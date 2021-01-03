<%--
  Created by IntelliJ IDEA.
  User: drweb
  Date: 05.05.2018
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Esteamer</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/user.css">
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
    <table class="table">
        <tr>
            <td>
                <label class="dline">Номер карты:</label> <%=request.getAttribute("number_card")%><br>
                <label class="dline">Дата выдачи:</label> <%=request.getAttribute("date_create")%><br>
                <label class="dline">Место выдачи:</label> <%=request.getAttribute("magazine")%><br>
                <label class="dline">ФИО клиента:</label><br> <%=request.getAttribute("fio")%><br>
                <label class="dline">День рождения:</label> <%=request.getAttribute("date_born")%><br>
                <label class="dline">№ телефона:</label> <%=request.getAttribute("telephone")%><br>
            </td>
            <td>
                <label class="dline">Статус карты:</label> <%=request.getAttribute("valid")%><br>
                <label class="dline">Процент:</label> <%=request.getAttribute("procent")%> %<br>
                <label class="dline">Общая сумма покупок:</label> <%=request.getAttribute("all_sum")%><br>
                <label class="dline">Сумма кешбека:</label> <output id="casbeks"> <%=request.getAttribute("cashbek")%></output><br>
                <label class="dline">Допольнительные бонусы:</label> <output id="gift"><%= request.getAttribute("gift")%></output><br>
                <label class="dline">Общая сумма накоплений:</label> <%=request.getAttribute("all_cashbek")%><br>
                <label class="dline">Дата последней покупки:</label> <%=request.getAttribute("date_buy")%><br>
                <label class="dline">Магазин последней покупки:</label> <%=request.getAttribute("magazine_last")%><br>
            </td>
        </tr>
    </table>
    <p></p>
    <table class="table">
        <tr>
            <td align="center"><h3>Покупка за денежные средства</h3></td>
        </tr>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/userview/summoney" method="post" onsubmit="CheckRadio('sposobzadengi',this); return false;">
                    <label class="dline">Способ оплаты:</label>
                        <input type="radio" name="sposobzadengi" value="nal">Наличными
                        <input type="radio" name="sposobzadengi" value="beznal">Безналичными или 4+1<br>
                        <input type="checkbox" name="procent_10" value="10">Товар под 10%<br>
                        <label class="dline">Сумма текущей покупки =</label> <input required type="text" name="new_sum" id="new_sum" onkeypress="return validate(event, this.id)"><br>
                        <input type="submit" value="Добавить">
                </form>
            </td>
        </tr>
    </table>
    <p></p>
    <% if (request.getAttribute("valid").equals("Активна")){%>
    <table class="table">
        <tr>
            <td align="center"><h3>Покупка за дененжные средства и бонусы</h3></td>
        </tr>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/userview/sumcashbak" method="post" onsubmit="CheckRadio('sposob',this); return false;">
                    <label class="dline">Способ доплаты:</label>
                        <input type="radio" name="sposob" value="nal">Наличными
                        <input type="radio" name="sposob" value="beznal">Безналичными или 4+1
                        <br><input type="checkbox" name="procent_10" value="10">Товар под 10%
                        <%--<br>Сумма покупки = <input type="text" name="sum" id="sum" onkeypress="return validate(event, this.id)" onchange="validCashbek(this.id), Kof()">--%>
                        <%--<br>Сумма олаты из накоплений = <input required type="text" name="cashbek" id="cashbek" readonly onkeypress="return validate(event,this.id)" onchange="validCash()">--%>
                        <%--<br>Доплата = <input required type="text" name="nal" id="nal" onkeypress="return validate(event,this.id)" onchange="validNal(), Kof()">--%>
                        <%--<br>Что приобретено : <input required type="text" name="comment" minlength="10" maxlength="255">--%>
                    <div id="conteiner1">
                        <div id="cont1">
                            <label class="dline">Сумма покупки =</label><br>
                            <label class="dline">Сумма олаты из накоплений =</label><br>
                            <label class="dline">Доплата =</label>
                            <%--<p class="dline">Сумма покупки =</p>--%>
                            <%--<p class="dline">Сумма олаты из накоплений =</p>--%>
                            <%--<p class="dline">Доплата =</p>--%>
                        </div>
                        <div id="cont2">
                            <input type="text" class="text" name="sum" id="sum" onkeypress="return validate(event, this.id)" onchange="validCashbek(this.id), Kof()">
                            <input required type="text" class="text" name="cashbek" id="cashbek" readonly onkeypress="return validate(event,this.id)" onchange="validCash()">
                            <input required type="text" class="text" name="nal" id="nal" onkeypress="return validate(event,this.id)" onchange="validNal(), Kof()">
                        </div>
                    </div>
                    <div id="kof"></div>
                    <label class="dline">Что приобретено :</label><br>
                    <textarea required rows="5" minlength="10" maxlength="255" id="comment" cols="50" name="comment" onkeyup="textarea(this.id)" onkeypress="textarea(this.id)"></textarea>
                    <br><div id="ost"></div>
                    <br><input type="submit" value="Добавить">
                </form>
            </td>
        </tr>
    </table>
    <%}%>
</div>
</body>
</html>

<script type="text/javascript">

    function textarea(element_id) {
        var obj = document.getElementById(element_id);
        // if (obj.value.length>255){
        //     document.getElementById(element_id).value = obj.value.substr(1,255);
        // }
        document.getElementById("ost").innerHTML = '(Осталось символов: '+(255-obj.value.length)+')';
    }

    // function validCashbek(element_id, name) {
    //     var obj = parseFloat(document.getElementById(element_id).value);//10
    //     var obj1 = parseFloat(document.getElementById("casbeks").value)+parseFloat(document.getElementById("gift").value);//75
    //     var obj3 = document.getElementsByName(name);
    //     var flag = false;
    //     var result = 0;
    //     for (var i = 0; i < obj3.length; i++){
    //         if (obj3[i].checked){
    //             flag = true;
    //             result = i;
    //         }
    //     }
    //     if (flag){ //&& result==2
    //         if (obj1<(obj*0.8)){
    //             document.getElementById("cashbek").value = (Math.round(obj1*10)/10);
    //             document.getElementById("nal").value = (Math.round((obj-obj1)*10)/10);
    //         }else{
    //             document.getElementById("cashbek").value = (Math.round((obj*0.8)*10)/10);
    //             document.getElementById("nal").value = (Math.round((obj-(obj*0.8))*10)/10);
    //         }
    //     }else if (flag && result==3){
    //         document.getElementById("cashbek").value = 0;
    //         document.getElementById("nal").value = obj;
    //     }
    //
    // }

    function validCashbek(element_id) {
        var obj = parseFloat(document.getElementById(element_id).value);//10
        var obj1 = parseFloat(document.getElementById("casbeks").value)+
            parseFloat(document.getElementById("gift").value);
            if (obj1<(obj*0.8)){
                document.getElementById("cashbek").value = (Math.round(obj1*10)/10);
                document.getElementById("nal").value = (Math.round((obj-obj1)*10)/10);
            }else{
                document.getElementById("cashbek").value = (Math.round((obj*0.8)*10)/10);
                document.getElementById("nal").value = (Math.round((obj-(obj*0.8))*10)/10);
            }
    }
    function validCash() {
        var obj = parseFloat(document.getElementById("cashbek").value);
        var obj1 = parseFloat(document.getElementById("casbeks").value)+
            parseFloat(document.getElementById("gift").value);
        var obj2 = parseFloat(document.getElementById("sum").value);
        if ((obj1*0.8)<obj){
            document.getElementById("cashbek").value = Math.round((obj1)*10)/10;
            document.getElementById("nal").value = Math.round((obj2-obj1)*10)/10;
        }else if ((obj2*0.8)<obj) {
            document.getElementById("cashbek").value = Math.round((obj2*0.8)*10)/10;

        }else{
            document.getElementById("nal").value = Math.round((obj2-obj)*10)/10;
        }
    }
    function validNal() {
        var nal = parseFloat(document.getElementById("nal").value);//20
        var allcasbek = parseFloat(document.getElementById("casbeks").value)+
            parseFloat(document.getElementById("gift").value);//750
        var sum = parseFloat(document.getElementById("sum").value);//100
        if ((sum*0.8)<allcasbek){
            if ((sum-nal)<=(sum*0.8)){
                document.getElementById("cashbek").value = Math.round((sum-nal)*10)/10;
            }else{
                document.getElementById("cashbek").value = Math.round((sum*0.8)*10)/10;
                document.getElementById("nal").value = Math.round((sum-(sum*0.8))*10)/10;
            }
        }else{
            if ((sum-nal)<=allcasbek){
                document.getElementById("cashbek").value = Math.round((sum-nal)*10)/10;
            }else {
                document.getElementById("cashbek").value = Math.round(allcasbek*10)/10;
                document.getElementById("nal").value = Math.round((sum-allcasbek)*10)/10;
            }
        }
    }

    function Kof() {
        var nal = parseFloat(document.getElementById("nal").value);
        var sum = parseFloat(document.getElementById("sum").value);
        document.getElementById("kof").innerHTML = '<h3>Коэфициент: '+
            Math.round((nal/sum)*100)/100+'</h3>';
    }


    function CheckRadio(name, form) {
        var obj = document.getElementsByName(name);
        var flag = false;
        for (var i = 0; i < obj.length; i++){
            if (obj[i].checked){
                flag = true;
            }
        }
        if (flag == false){
            alert("Не выбран способ оплаты!");
        }else{
            form.submit();
        }

    }
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
            // if(charCode==46){
            //     var txt=document.getElementById(id).value;
            //     if(!(txt.indexOf(".") > -1)){
            //
            //         return true;
            //     }
            // }
            if (charCode > 31 && (charCode < 48 || charCode > 57) )
                return false;

        }catch(w){
        }
    }
</script>
