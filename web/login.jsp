<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <link href="resources/css/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<div id="main_div">
    <jsp:include page="navbar.jsp"/>
    <div id="login_body_div">

        <form name="" action="/" method="">
            <div class="login-search-container">
                <input class="tftextinput" type="text" placeholder="Log in" name="login" value="">
            </div>
            <div class="login-search-container">
                <input class="tftextinput" type="password" placeholder="Password" name="password" value="">
            </div>
            <div class="login-search-container">
                <button type="submit" class="tfbutton login_button">Log in</button>
            </div>
        </form>

        <form name="" action="/" method="">
            <div class="login-search-container">
                <input class="tftextinput" type="text" placeholder="Name" name="user_name" value="">
            </div>
            <div class="login-search-container">
                <input class="tftextinput" type="text" placeholder="Email" name="user_email" value="">
            </div>
            <div class="login-search-container">
                <input class="tftextinput" type="text" placeholder="Age" name="user_age" value="">
            </div>
            <div class="login-search-container">
                <input class="tftextinput" type="text" placeholder="Log in" name="login" value="">
            </div>
            <div class="login-search-container">
                <input class="tftextinput" type="password" placeholder="Password" name="password" value="">
            </div>
            <div class="login-search-container">
                <input class="tftextinput" type="password" placeholder="Confirm password" name="password" value="">
            </div>
            <div class="login-search-container">
                <button type="submit" class="tfbutton login_button">Create account</button>
            </div>
        </form>

    </div>
</div>
</body>
</html>