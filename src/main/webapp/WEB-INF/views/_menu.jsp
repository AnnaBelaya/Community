<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="default-menu">
    <a href="/web/students">Students</a> |
    <a href="/web/rooms">Rooms</a>
    <sec:authorize access="hasAuthority('ADMIN')">
        | <a href="/web/admins">Admins</a>
    </sec:authorize>
    <a href="/logout" style="float: right">Log out</a>
</div>