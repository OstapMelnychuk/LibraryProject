<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="resources/css/collapse.css" type="text/css">

<div id="custom-bootstrap-menu" class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header"><a class="navbar-brand" href="#"><span class="glyphicon glyphicon-fire"></span></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-menubuilder"><span
                    class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span
                    class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-menubuilder">
            <ul class="nav navbar-nav navbar-left">
                <c:if test="${admin == 1 || admin == 2}"><li><a href="/home" class="hvr-underline-reveal">Home</a></li></c:if>
                <c:if test="${admin == 1 || admin == 2}"><li><a href="/books" class="hvr-underline-reveal">Books</a></li></c:if>
                <c:if test="${admin == 1}"><li><a href="/users" class="hvr-underline-reveal">Users</a></li></c:if>
                <c:if test="${admin != 1 && admin != 2}"> <li><a href="/home" class="hvr-underline-reveal">         </a></li> </c:if>
                <c:if test="${admin == 1}"><li><a href="/addBook" class="hvr-underline-reveal">Add book</a></li></c:if>
                <div class="navbar-text mr-3  login-text">
                    <a href="/login"><c:if test="${admin == 1 || admin == 2}">Log out</c:if><c:if test="${admin != 1 && admin != 2}">Log in</c:if></a>
                </div>
            </ul>
        </div>

    </div>
</div>