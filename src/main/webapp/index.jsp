<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>RT Labs Welcome</title>
    <link href="<c:url value="/resources/img/favicon.ico"/>" rel="shortcut icon" type="image/x-icon">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
</head>
<body>
<div id="main_welcome">
<div id="label_text">RT LABS Подача заявки</div>
<div id="open"><a href="${pageContext.request.contextPath}/declare">Открыть</a></div>
</div>

</body>
</html>
