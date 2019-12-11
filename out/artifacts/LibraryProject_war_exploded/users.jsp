<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Library</title>
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
                            Search by user name
                        </button>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        <form class="form-inline" method="post" action="/userStatistic">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="user name" class="sr-only">User's name</label>
                                <input type="text" class="form-control" id="user name" name="user"
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
                            Search by title AVG user info
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
                            Search by author AVG user info
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

    <div class="for">
        <c:forEach items="${users}" var="element">
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

