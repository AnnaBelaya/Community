<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Community. New Room</title>
    <link rel="stylesheet" href="../../resources/styles/default.css"/>
</head>
<body>
<div id="container">
    <form:form action="/web/rooms/add" method="post" commandName="room">
        <h2>New room</h2>
        <hr/>
        <table>
            <tr>
                <td>
                    <form:label for="number" path="number">Number</form:label>
                </td>
                <td>
                    <form:input type="text" name="number" placeholder="number" path="number"/>
                    <br/><form:errors path="number" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="capacity" path="capacity">Capacity</form:label>
                </td>
                <td>
                    <form:select type="text" name="capacity" path="capacity">
                        <form:option value="2"/>
                        <form:option value="3"/>
                        <form:option value="4"/>
                        <form:option value="5"/>
                    </form:select>
                    <br/><form:errors path="capacity" cssClass="errorMessage"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Add</button>
                </td>
                <td>
                    <button type="reset">Cancel</button>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>