<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="relate">
    <div class="left-collapse">
        <div id="accordion">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="false" aria-controls="collapseTwo">
                            Search by user name
                        </button>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse <c:if test="${name_show}">in</c:if>" aria-labelledby="headingOne"
                     data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/user-requests">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="name" class="sr-only">name</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="name">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2">Search</button>
                        </form>
                    </div>
                </div>
            </div>


            <div class="card">
                <div class="card-header" id="headingTwo">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo"
                                aria-expanded="false" aria-controls="collapseTwo">
                            Search by little AVG user info
                        </button>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-title">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="title" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="title">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2">Search</button>
                        </form>
                    </div>
                </div>
            </div>


            <div class="card">
                <div class="card-header" id="headingThree">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree"
                                aria-expanded="false" aria-controls="collapseThree">
                            Search by author ABG user info
                        </button>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-year">
                            <div class="form-group mx-sm-3 mb-2 first-year">
                                <label for="first-year" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="first-year" name="first-year"
                                       placeholder="first year">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2 year-button">Search</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header" id="headingFour">
                    <h5 class="mb-0">
                        <form class="form-inline" method="post" action="/debtors">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour"
                                    aria-expanded="false" aria-controls="collapseFour">
                                List of debtors
                            </button>
                        </form>
                    </h5>
                </div>
            </div>


            <div class="card">
                <div class="card-header" id="headingFive">
                    <h5 class="mb-0">
                        <form class="form-inline" method="post" action="/top">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive"
                                    aria-expanded="false" aria-controls="collapseFive">
                                Users average statistic
                            </button>
                        </form>

                    </h5>
                </div>
            </div>
        </div>
    </div>


    <c:if test="${not empty userh}">
    <li class="list-group-item d-flex justify-content-between align-items-center li_button">
            ${userh.nickName}
        <span class="badge badge-primary badge-pill">${userh.login}</span>
        <div>
                ${userh.email} ${userh.startDay}
        </div>
            ${time}

        <div class="for">
            <c:forEach items="${book1}" var="element">
                 <li class="list-group-item d-flex justify-content-between align-items-center li_button">
                 ${element.title}
                <div>
                ${element.author.name} ${element.author.secondname}
                </div>

                </li>
                </c:forEach>
</div>
<div class="for">
    <c:forEach items="${book2}" var="element">
        <li class="list-group-item d-flex justify-content-between align-items-center li_button">
                ${element.title}
            <div>
                    ${element.author.name} ${element.author.secondname}
            </div>

        </li>
    </c:forEach>
</div>
</li>

</c:if>


<div class="for">
    <c:forEach items="${users}" var="element">
        <li class="list-group-item d-flex justify-content-between align-items-center li_button">
                ${element.nickName}
            <span class="badge badge-primary badge-pill">${element.login}</span>
            <div>
                    ${element.email} ${element.startDay}
            </div>

        </li>
    </c:forEach>
</div>

</div>

</body>
</html>

