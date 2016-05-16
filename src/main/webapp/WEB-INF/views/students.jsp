<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Community. Students</title>
    <link rel="stylesheet" href="../../resources/styles/default.css"/>
</head>
<body>
<sec:authentication var="principal" property="principal"/>
<div id="container">
<h2>Students</h2>
<hr/>
<c:if test="${empty students}">
    <p>There are no students in database</p>
</c:if>
<c:if test="${not empty students}">
    <table>
        <tr>
            <th>Full Name</th>
            <th>Room</th>
        </tr>
        <c:forEach items="${students}" var="student">
            <c:if test='${principal.username == student.email}'>
                <c:set value="authUser" var="authUser"/>
            </c:if>
            <tr>
                <td><a href="/web/students/${student.id}" class="${authUser}">
                    ${student.lastName} ${student.firstName} ${student.patronymic}
                </a></td>
                <c:if test="${student.room != null}">
                    <td><a href="/web/rooms/${student.room.number}">
                        ${student.room.number}
                    </a></td>
                </c:if>
            </tr>
            <c:set value="" var="authUser"/>
        </c:forEach>
    </table>
</c:if>
    <br/>
<%@include file="_menu.jsp"%>
</div>
</body>
</html>