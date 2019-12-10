<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="resources/js/jQuery.js"></script>
    <link href="resources/css/collapse.css" rel="script" type="text/css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="relate">
    <div class="left-collapse">
        <div id="accordion">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" aria-expanded="false"
                                data-target="#collapseOne"
                                aria-controls="collapseTwo">
                            Search by Author
                        </button>
                    </h5>
                </div>

                <div id="collapseOne" class="collapse <c:if test="${author_show}">in</c:if>" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body cart">
                        <form class="form-inline" method="post" action="/search-author">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="authors name" class="sr-only">Author's name</label>
                                <input type="text" class="form-control" id="authors name" name="author"
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
                            Search by Title
                        </button>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse <c:if test="${title_show}">in</c:if>" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-title">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="title" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="title">
                            </div>
                            <button id="home" type="submit" class="btn btn-primary mb-2">Search</button>
                        </form>
                    </div>
                </div>
            </div>


            <div class="card">
                <div class="card-header" id="headingThree">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree"
                                aria-expanded="false" aria-controls="collapseThree">
                            Search by years
                        </button>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse <c:if test="${year_show}">in</c:if>" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/search-year">
                            <div class="form-group mx-sm-3 mb-2 first-year">
                                <label for="first-year" class="sr-only">First year</label>
                                <input type="text" class="form-control" id="first-year" name="first-year"
                                       placeholder="first year">
                            </div>
                            <div class="form-group mx-sm-3 mb-2 first-year">
                                <label for="last-year" class="sr-only">Last year</label>
                                <input type="text" class="form-control" id="last-year" name="last-year"
                                       placeholder="last year">
                            </div>
                            <br>
                            <small id="emailHelp" class="form-text text-muted" style="color: deeppink"><c:if
                                    test="${not empty error}">${error}</c:if></small>
                            <br>
                            <button type="submit" class="btn btn-primary mb-2 year-button">Search</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header" id="headingFour">
                    <h5 class="mb-0">
                        <form class="form-inline" method="post" action="/back-top">
                            <button class="btn btn-link collapsed" data-toggle="collapse"
                                    data-target="#collapseFour"
                                    aria-expanded="false" aria-controls="collapseFour">
                                Top 10 books
                            </button>
                        </form>
                    </h5>
                </div>
            </div>


            <div class="card">
                <div class="card-header" id="headingFive">
                    <h5 class="mb-0">
                        <form class="form-inline" method="post" action="/top">
                            <button class="btn btn-link collapsed"
                                    data-toggle="collapse" data-target="#collapseFive"
                                    aria-expanded="false" aria-controls="collapseFive">
                                Top unknown 10 books
                            </button>
                        </form>

                    </h5>
                </div>
            </div>
        </div>
    </div>
    <div class="for">
        <c:choose>

            <c:when test="${not empty books}">
                <c:forEach items="${books}" var="element">
                    <li class="list-group-item d-flex justify-content-between align-items-center li_button">
                            Title: ${element.title}
                        <span class="badge badge-primary badge-pill"
                              <c:if test="${not element.isAvailable()}">style="background-color: #d31444"</c:if>
                              <c:if test="${element.isAvailable()}">style="background-color: #4adf75"</c:if>>

                    <c:if test="${element.isAvailable()}"> is available</c:if>

                    <c:if test="${not element.isAvailable()}">is not available</c:if>
                </span>
                        <div>
                                Author: ${element.author.name} ${element.author.secondname}
                        </div>
                    </li>
                </c:forEach>
            </c:when>

            <c:when test="${empty books && !isListExist}">
                <h3 style="color:#ca5757;">Sorry, but we don't have such books(</h3>
            </c:when>

        </c:choose>
    </div>
</div>
</div>

</body>
</html>

