<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://ru.javawebinar.topjava/functions" prefix="f" %>
<html lang="ru">
<head>
    <title>Update Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<table width="80%" border="1" align="center">
  <tr>
    <th>Дата/Время</th>
    <th>Описание</th>
    <th>Калории</th>
  </tr>
    <tr>
      <td><c:out value="${f:formatLocalDateTime(requestScope.mealBefore.dateTime, 'yyyy-MM-dd HH:mm')}"/></td>
      <td><c:out value="${requestScope.mealBefore.description}"/></td>
      <td><c:out value="${requestScope.mealBefore.calories}"/></td>
    </tr>
</table>

<form method="post" action="">
  Set dateTime: <label><input type="datetime-local" name="date"></label><br>
  Set description:<label><input type="text" name="description"></label><br>
  Set calories: <label><input type="number" name="calories"></label><br>
  <input type="submit" value="Save" name="Save">
  <input type="reset" value="Cancel" name="Cancel">
</form>

</body>
</html>
