<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="CashBak" class="CashBack.CashBack" scope="page"/>
<html>
<head>
    <title>Esteamer</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/logo.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css" type="text/css">
    <meta http-equiv="refresh" content="60; URL='${pageContext.request.contextPath}/userview/user.jsp'" />
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
<h2>Результат проведенной операции!</h2>
<div id="conttable">
<table class="table">
    <tr>
        <td align="center" colspan="2">Информация о клиенте</td>
    </tr>
    <tr>
        <td>Клиент: </td>
        <td><%=request.getAttribute("client_fio")%></td>
    </tr>
    <tr>
        <td>Номер карты: </td>
        <td><%=session.getAttribute("number_card")%></td>
    </tr>
    <tr>
        <td>Номер транзакции:</td>
        <td><%=request.getAttribute("id_sale")%></td>
    </tr>
    <tr>
        <td align="center" colspan="2">Информация о покупке</td>
    </tr>
    <tr>
        <td>Сумма покупки: </td>
        <td><%=request.getAttribute("new_sum")%></td>
    </tr>
    <tr>
        <td>Сумма кешбека: </td>
        <td><%=request.getAttribute("sum_cashbek")%></td>
    </tr>
    <tr>
        <td>Доп бонусы</td>
        <td><%=request.getAttribute("sum_gift")%></td>
    </tr>
    <tr>
        <td align="center" colspan="2">Информация по карте</td>
    </tr>
    <tr>
        <td>Процент: </td>
        <td><%=request.getAttribute("procent")%></td>
    </tr>
    <tr>
        <td>Сумма покупок: </td>
        <td>${CashBak.getSum(allsum)}</td>
    </tr>
    <tr>
        <td>Сумма кешбек: </td>
        <td>${CashBak.getSum(cashbek)}</td>
    </tr>
    <tr>
        <td>Доп бонусы: </td>
        <td>${CashBak.getSum(gift)}</td>
    </tr>
    <tr>
        <td>Сумма бонусов: </td>
        <td>${CashBak.getSum(allcashbek)}</td>
    </tr>
    <tr>
        <td align="center" colspan="2">Повышение процента</td>
    </tr>
    <c:set var="procent" value="${CashBak.procent_discont}"/>
    <c:forEach var="sum" items="${CashBak.sum_discount}" varStatus="i">
        <c:if test="${allsum <= sum}">
            <tr>
                <td>${procent[i.index]}%</td>
                <td>${CashBak.getSum(sum - allsum)}</td>
            </tr>
        </c:if>
    </c:forEach>
    <%--<tr>--%>
        <%--<td>3%</td>--%>
        <%--<td><%=request.getAttribute("three")%></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>5%</td>--%>
        <%--<td><%=request.getAttribute("five")%></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>7%</td>--%>
        <%--<td><%=request.getAttribute("seven")%></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>10%</td>--%>
        <%--<td><%=request.getAttribute("ten")%></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>12%</td>--%>
        <%--<td><%=request.getAttribute("twelwe")%></td>--%>
    <%--</tr>--%>
</table>
</div>
</body>
</html>
