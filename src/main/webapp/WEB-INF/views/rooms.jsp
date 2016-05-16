<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Community. Rooms</title>
    <link rel="stylesheet" href="../../resources/styles/default.css"/>
</head>
<body>
<sec:authentication var="principal" property="principal"/>
<div id="container">
<h2>Rooms</h2>
<hr/>
<c:if test="${empty rooms}">
    <p>There are no rooms in database</p>
</c:if>
<c:if test="${not empty rooms}">
    <table>
        <tr>
            <th>Number</th>
            <th>Count</th>
            <th>Occupants</th>
            <sec:authorize access="hasAuthority('ADMIN')">
                <th>Options</th>
            </sec:authorize>
        </tr>
        <c:forEach items="${rooms}" var="room">
            <c:set var="studentsSize">${fn:length(room.students)}</c:set>
            <tr>
                <td><a href="/web/rooms/${room.number}">${room.number}</a></td>
                <td>${studentsSize}/${room.capacity}</td>
                <td>
                    <c:forEach items="${room.students}" var="student" varStatus="loop">
                        <c:if test='${student.email eq principal.username}'>
                            <c:set value="authUser" var="authUser"/>
                        </c:if>
                        <a href="/web/students/${student.id}" class="${authUser}">${student}</a>
                        <c:if test="${loop.index < (studentsSize - 1)}">, </c:if>
                        <c:set value="" var="authUser"/>
                    </c:forEach>
                </td>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <td>
                        <a href="/web/rooms/${room.number}/remove">remove</a>
                        | <a href="/web/rooms/${room.number}/clear">clear</a>
                    </td>

                </sec:authorize>
            </tr>
        </c:forEach>
        <sec:authorize access="hasAuthority('ADMIN')">
            <tr>
                <td colspan="4"><a href="/web/rooms/add">Add new room...</a></td>
            </tr>
        </sec:authorize>
    </table>
</c:if>
    <br/>
    <%@include file="_menu.jsp"%>
</div>
</body>
</html>