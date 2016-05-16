<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Community. Student</title>
    <link rel="stylesheet" href="../../resources/styles/default.css"/>
</head>
<body>
<div id="container">
    <sec:authentication var="principal" property="principal"/>
    <h2>Student</h2>
    <hr/>
    <p id="student-full-name"><strong>${student.lastName} ${student.firstName} ${student.patronymic}</strong>
        <c:if test="${principal.username == student.email}">
            (<a href="/web/students/${student.id}/profile">edit profile</a>)
        </c:if>
    </p>
    <p>Room: <span id="student-room"><a href="/web/rooms/${student.room.number}">${student.room}</a></span></p>
    <p id="student-email">${student.email}</p>
    <br/>
    <%@include file="_menu.jsp"%>
</div>
</body>
</html>