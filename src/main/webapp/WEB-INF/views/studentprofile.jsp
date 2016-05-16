<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Community. Student profile</title>
    <link rel="stylesheet" href="../../../resources/styles/default.css"/>
</head>
<body>
<sec:authentication var="principal" property="principal"/>
<div id="container">

    <h2>Student profile</h2>
    <hr/>
    <form:form action="/web/students/${student.id}/profile" method="post" commandName="student">
        <table>
            <tr>
                <td>
                    <form:label for="username" path="email">E-mail</form:label>
                </td>
                <td>
                    <form:input type="text" name="username" placeholder="e-mail" path="email"/>
                    <br/><form:errors path="email" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="password" path="password">Password</form:label>
                </td>
                <td>
                    <form:input type="password" name="password" placeholder="password" path="password"/>
                    <br/><form:errors path="password" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="lastName" path="lastName">Last name</form:label>
                </td>
                <td>
                    <form:input type="text" name="lastName" placeholder="last name" path="lastName"/>
                    <br/><form:errors path="lastName" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="firstName" path="firstName">First name</form:label>
                </td>
                <td>
                    <form:input type="text" name="firstName" placeholder="first name" path="firstName"/>
                    <br/><form:errors path="firstName" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="patronymic" path="patronymic">Patronymic</form:label>
                </td>
                <td>
                    <form:input type="text" name="patronymic" placeholder="patronymic" path="patronymic"/>
                    <br/><form:errors path="patronymic" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Update</button>
                </td>
                <td>
                    <button type="reset">Cancel</button>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <%@include file="_menu.jsp"%>
</div>
</body>
</html>