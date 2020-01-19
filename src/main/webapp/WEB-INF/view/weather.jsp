<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=ISO-8859-1">
    <title>User Registration</title>
</head>
<body>
<h1>${weather.city}, ${weather.state}</h1>

<p>${weather.temperature}&deg; <span>${weather.summary}</span>
    <canvas id="icon1" width="128" height="128"></canvas></p>
</body>
<script src="${pageContext.request.contextPath}/skycons.js"></script>
<script>
    var applicationVariables = {
        "icon": "${weather.icon}"
    };
</script>
<script src="${pageContext.request.contextPath}/App.js"></script>
</html>