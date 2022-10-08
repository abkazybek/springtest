<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="newsForm">
        <h2>Cоздание товара</h2>
        <div>
            <form:input type="text" path="id" placeholder="id"  autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="name" placeholder="name"  autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="comment" placeholder="comment"  autofocus="true"></form:input>
        </div>
        <button type="submit">Создать</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>