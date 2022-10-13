<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://ru.javawebinar.topjava/functions" prefix="f" %>
<html lang="ru">

<head>
    <title>Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    Set dateTime: <label><input type="datetime-local" value="${meal.dateTime}" name="date"></label><br>
    Set description:<label><input type="text" value="${meal.description}" name="description"></label><br>
    Set calories: <label><input type="number" value="${meal.calories}" name="calories"></label><br>
    <input type="submit" value="Save" name="Save">
    <input type="reset" value="Cancel" name="Cancel">
</form>

</body>
</html>
