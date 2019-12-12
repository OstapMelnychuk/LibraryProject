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
            <form method="post" action="/registration-user">
                <div class="form-group">
                    <label for="user_name">Name</label>
                    <input type="text" class="form-control" id="user_name" aria-describedby="emailHelp"
                           placeholder="Enter your name" name = "user_name">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter your email"
                           name = "email">
                </div>
                <div class="form-group">
                    <label for="user_age">Age</label>
                    <input type="text" class="form-control" id="user_age" aria-describedby="emailHelp"
                           placeholder="Enter your age" name = "user_age">
                </div>
                <div class="form-group">
                    <label for="regist_login">Login</label>
                    <input type="text" class="form-control" id="regist_login" aria-describedby="emailHelp"
                           placeholder="Enter your login" name = "regist_login">
                </div>
                <div class="form-group">
                    <label for="regist_password">Password</label>
                    <input type="password" class="form-control" id="regist_password" placeholder="Password"
                    name="regist_password">
                </div>
                <div class="form-group">
                    <label for="confirm_password">Confirm password</label>
                    <input type="password" class="form-control" id="confirm_password" placeholder="Confirm password"
                    name="confirm_password">
                </div>
                <button type="submit" class="btn btn-primary">Create account</button>
            </form>
        </div>
    </div>
</div>
<div style="color: gold; text-align: center;">
    <h2>${message}</h2>
</div>
</body>
</html>