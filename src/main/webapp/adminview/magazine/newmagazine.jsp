
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
        <form action="${pageContext.request.contextPath}/adminview/magazine.jsp">
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
        <form action="${pageContext.request.contextPath}/adminview/newmagazine" method="post">
            <table class="table">
                <tr>
                    <td colspan="2" align="center"><label class="dline">Добавление нового магазина</label> </td>
                </tr>
                <tr>
                    <td><label class="dline">Название</label></td>
                    <td><input type="text" name="name_m"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
