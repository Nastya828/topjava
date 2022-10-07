<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://ru.javawebinar.topjava/functions" prefix="f" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<ul>
    <li><a href="addMeals.jsp">Add Meal</a></li>
</ul>
<hr>
<table width="100%" border="1" align="center">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>

    <c:forEach items="${mealsForTables}" var="meal">
        <tr style="color:${!meal.excess ? 'green' : 'red'}">
            <td><c:out value="${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
