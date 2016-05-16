<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Community. Admins</title>
    <link rel="stylesheet" href="../../resources/styles/default.css"/>
</head>
<body>
<div id="container">
    <h2>Admins</h2>
    <hr/>
    <c:if test="${empty admins}">
        <p>There are no admins in database</p>
    </c:if>
    <c:if test="${not empty admins}">
        <table>
            <tr>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Patronymic</th>
            </tr>
            <c:forEach items="${admins}" var="admin">
                <tr>
                    <td>${admin.lastName}</td>
                    <td>${admin.firstName}</td>
                    <td>${admin.patronymic}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br/>
    <%@include file="_menu.jsp"%>
</div>
</body>
</html>