<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Прием заявок</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
  <table>
    <thead>
    <th>ID товара</th>
    <th>Название</th>
    <th>Количество</th>
    <th>Адрес доставки</th>
    <th>Номер телефона</th>
    </thead>
    <c:forEach items="${allOrders}" var="order">
      <tr>
        <td>${order.id}</td>
        <td>${order.name}</td>
        <td>${order.amount}</td>
        <td>${order.addres}</td>
        <td>${order.telephone}</td>
        <td>
      </tr>
    </c:forEach>
  </table>
  <a href="/">Главная</a>
</div>
</body>
</html>