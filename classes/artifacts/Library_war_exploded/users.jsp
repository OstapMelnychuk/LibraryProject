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
                <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-author-name">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="authors-name" class="sr-only">Author's name</label>
                                <input type="text" class="form-control" id="authors-name" name="author"
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
                            Get average user age by book title
                        </button>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-title-age">
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
                            Search by author AVG user info
                        </button>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-author-age">
                            <div class="form-group mx-sm-3 mb-2 first-year">
                                <label for="author-name" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="author-name" name="author-name"
                                       placeholder="author-name">
                                <label for="author-secondname" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="author-secondname" name="author-secondname"
                                       placeholder="author-secondname">
                                <label for="author-surname" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="author-surname" name="author-surname"
                                       placeholder="author-surname">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2 year-button">Search</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header" id="headingFour">
                    <h5 class="mb-0">
                        <form class="form-inline" method="post" action="/back-top">
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

    <div class="for">
        <c:choose>
            <c:when test="${not empty authors}">
                <c:forEach items="${authors}" var="element">
                    <li class="list-group-item d-flex justify-content-between align-items-center li_button">
                            ${element.login}
                            ${element.email}
                    </li>
                </c:forEach>
            </c:when>
            <c:when test="${age > 0}">
                <li class="list-group-item d-flex justify-content-between align-items-center li_button"> ${age} </li>
            </c:when>
            <c:when test="${age <= 0}">
                <h3>Sorry we don`t have statistic for such book</h3>
            </c:when>
            <c:when test="${empty authors && authors.isEmpty()}">
                <h3>Sorry there are no such users</h3>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>

