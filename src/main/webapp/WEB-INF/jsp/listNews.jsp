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
    <th>ID новости</th>
    <th>Дата создания</th>
    <th>Название</th>
    <th>Комментарий</th>
    </thead>
    <c:forEach items="${allNews}" var="news">
      <tr>
        <td>${news.id}</td>
        <td>${news.created_at}</td>
        <td>${news.name}</td>
        <td>${news.comment}</td>
        <td>
      </tr>
    </c:forEach>
  </table>
  <a href="/">Главная</a>
</div>
</body>
</html>