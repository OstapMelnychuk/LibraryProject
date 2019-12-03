<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Bootstrap Case</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="relate">
    <div class="left-collapse">
        <div id="accordion">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="false" aria-controls="collapseTwo">
                            Search by Author
                        </button>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
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
                            Search by years
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
                            <div class="form-group mx-sm-3 mb-2 first-year">
                                <label for="last-year" class="sr-only">Title</label>
                                <input type="text" class="form-control" id="last-year" name="last-year"
                                       placeholder="last year">
                            </div>
                            <div></div>
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
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive"
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
        <c:forEach items="${books}" var="element">
            <li class="list-group-item d-flex justify-content-between align-items-center li_button">
                    ${element.title}
                <span class="badge badge-primary badge-pill">${element.dateOfPublishment}</span>
                <div>
                    ${element.description}
                </div>
            </li>
        </c:forEach>
    </div>

</div>

</body>
</html>

