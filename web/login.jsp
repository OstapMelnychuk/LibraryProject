<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <style type="text/css">

        .tftextinput {
            margin: 0;
            padding: 5px 15px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 14px;
            border: 1px solid #0076a3;
            border-right: 0px;
            border-top-left-radius: 5px 5px;
            border-bottom-left-radius: 5px 5px;
        }

        .tfbutton {
            margin: 0;
            padding: 5px 15px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 14px;
            outline: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            color: #ffffff;
            border: solid 1px #0076a3;
            border-right: 0px;
            background: #0095cd;
            background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
            background: -moz-linear-gradient(top, #00adee, #0078a5);
            border-top-right-radius: 5px 5px;
            border-bottom-right-radius: 5px 5px;
        }

        .tfbutton:hover {
            text-decoration: none;
            background: #007ead;
            background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
            background: -moz-linear-gradient(top, #0095cc, #00678e);
        }

        /* Fixes submit button height problem in Firefox */
        .tfbutton::-moz-focus-inner {
            border: 0;
        }

        .login-search-container {
            padding: 5px 5px;
            display: block;
            margin: 0 auto;
        }

        .login_button {
            width: 10%;
        }

        #login_body_div {
            width: 100%;
            text-align: center;
            background-color: #989390;
        }

    </style>
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