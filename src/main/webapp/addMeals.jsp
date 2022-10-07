<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Edit meal</title>
</head>
<body>

<h3><a href="index.html">Home</a></h3>

<form method="post" action="">
    DateTime: <label><input type="datetime-local" name="date"></label><br>
    Description:<label><input type="text" name="description"></label><br>
    Calories: <label><input type="number" name="calories"></label><br>
    <input type="submit" value="Save" name="Save">
    <input type="submit" value="Cancel" name="Cancel">
</form>


</body>
</html>
