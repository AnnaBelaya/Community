<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Community. Room</title>
    <link rel="stylesheet" href="../../resources/styles/default.css"/>
</head>
<body>
<div id="container">
    <h2>Room</h2>
    <hr/>
    <p id="room-number"><strong>${room.number}<sec:authorize access="hasAuthority('USER')">
        (<a href="/web/rooms/${room.number}/book">book</a>)
        </sec:authorize></strong></p>
    <p>Capacity: <span id="room-capacity">${room.capacity}</span></p>
    <c:if test="${empty room.students}">
        <p>There are no students in this room</p>
    </c:if>
    <c:if test="${not empty room.students}">
        <p>Occupants</p>
        <ul>
            <c:forEach items="${room.students}" var="student">
                <li><a href="/web/students/${student.id}">${student}</a></li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${fn:length(room.students) >= room.capacity}">
        <p>Room is full</p>
    </c:if>

    <br/>
    <%@include file="_menu.jsp"%>
</div>
</body>
</html>