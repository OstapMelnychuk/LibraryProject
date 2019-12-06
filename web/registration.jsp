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
            <form method="post" action="/">
                <div class="form-group">
                    <label for="user_name">Name</label>
                    <input type="text" class="form-control" id="user_name" aria-describedby="emailHelp"
                           placeholder="Enter your name">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter your email">
                </div>
                <div class="form-group">
                    <label for="user_age">Age</label>
                    <input type="text" class="form-control" id="user_age" aria-describedby="emailHelp"
                           placeholder="Enter your age">
                </div>
                <div class="form-group">
                    <label for="regist_login">Login</label>
                    <input type="text" class="form-control" id="regist_login" aria-describedby="emailHelp"
                           placeholder="Enter your login">
                </div>
                <div class="form-group">
                    <label for="regist_password">Password</label>
                    <input type="password" class="form-control" id="regist_password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="confirm_pasport">Confirm password</label>
                    <input type="password" class="form-control" id="confirm_pasport" placeholder="Confirm password">
                </div>
                <button type="submit" class="btn btn-primary">Create account</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>