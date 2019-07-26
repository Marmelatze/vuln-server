<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<s:form action="login">
    <s:textfield name="credentials.username" label="Username" />
    <s:textfield name="credentials.password" label="Password" />
    <s:hidden name="credentials.state"/>
    <s:submit value="Login" />
</s:form>

</body>
</html>
