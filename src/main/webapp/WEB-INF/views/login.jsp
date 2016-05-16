<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Community. Login</title>
    <link rel="stylesheet" href="../../resources/styles/default.css">
</head>
<body>

<div id="container">
    <form action="/auth/login" method="post">
        <h2>Please log in</h2>
        <hr/>
        <table>
            <tr>
                <td>
                    <label for="username">E-mail</label>
                </td>
                <td>
                    <input type="text" id="username" name="username" placeholder="e-mail" required autofocus/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Password</label>
                </td>
                <td>
                    <input type="password" id="password" name="password" placeholder="password" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Log in</button>
                </td>
                <td>
                    <a href="/auth/registration">Registration</a>
                </td>
            </tr>
        </table>
        <p>
        <c:if test="${param.error != null}">
            <span style="color: #8b0000">
                Invalid username or password.
            </span>
        </c:if>
        <c:if test="${param.logout != null}">
            <span>
                You have been logged out.
            </span>
        </c:if>
        </p>
    </form>
</div>
</body>
</html>