<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="resources/css/users.css" type="text/css">
</head>
<body>
<div id="main_div">
    <jsp:include page="navbar.jsp" />
    <style>
        #main_div {
            position: fixed;
            left: 50%;
            height: 70%;
            width: 70%;
            margin-left: -35%;
        }
    </style>
    <div id="body_div">
        <div id="search_form">
            <div class="search-container">
                <form name="" action="/" method="">
                    <input class="tftextinput" type="text" placeholder="Search by user name" name="user_name" value="">
                    <button type="submit" class="tfbutton">Search</button>
                </form>
            </div>
            <div class="search-container">
                <form name="" action="/" method="">
                    <input class="tftextinput" type="text" placeholder="Search by title Avg user info" name="book_title"
                           value="">
                    <button type="submit" class="tfbutton">Search</button>
                </form>
            </div>
            <div class="search-container">
                <form name="" action="/" method="">
                    <input class="tftextinput" type="text" placeholder="Search by Author Avg user info"
                           name="book_author" value="">
                    <button type="submit" class="tfbutton">Search</button>
                </form>
            </div>

            <div class="search-container">
                <form name="" action="/" method="">
                    <button type="submit" class="tfbutton long_button">List of debtors</button>
                </form>
            </div>
            <div class="search-container">
                <form name="" action="/" method="">
                    <button type="submit" class="tfbutton long_button">Users average statistic</button>
                </form>
            </div>

        </div>

        <div id="result">
            Comment: here will be the result of the search.
        </div>
    </div>
</div>
</body>
</html>