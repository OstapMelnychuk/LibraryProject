<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <link href="resources/css/loginAndCreate.css" rel="stylesheet" type="text/css">
    <title>Library</title>
</head>
<body>
<div id="main_div">
    <jsp:include page="navbar.jsp"/>



    <div id="login_body_div" class="login">
        <div>
            <form method="post" action="/login">
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" name="login" aria-describedby="login"
                           placeholder="Enter your login">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Log in</button>
            </form>
            <div><a href="registration.jsp" style="text-align: center">Sign up</a></div>
        </div>
    </div>
</div>
</body>
</html>