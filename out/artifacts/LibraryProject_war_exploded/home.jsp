<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="resources/css/collapse.css" type="text/css" rel="stylesheet">
    <link href="resources/css/home.css" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="row1" >
    <c:forEach items="${books}" var="element">
        <div class="column">
            <div class="card">
                   <h5> ${element.title}</h5>
                <div>${element.author.name} ${element.author.secondname}</div>
                <div class="badge badge-primary badge-pill" <c:if test="${element.isAvailable()}">style="background-color: #4adf75" </c:if>>

                       <c:if test="${element.isAvailable()}">is available</c:if>

                       <c:if test="${not element.isAvailable()}">is not available</c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>

