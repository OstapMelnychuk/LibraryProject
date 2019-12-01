<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/book.css" type="text/css">
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
                    <input class="tftextinput" type="text" placeholder="Search by author" name="search_book_author"
                           value="">
                    <button type="submit" class="tfbutton">Search</button>
                </form>
            </div>
            <div class="search-container">
                <form name="" action="/" method="">
                    <input class="tftextinput" type="text" placeholder="Search by book title" name="search_book_author"
                           value="">
                    <button type="submit" class="tfbutton">Search</button>
                </form>
            </div>
            <div class="search-container">
                <form name="" action="/" method="">
                    <input class="tftextinput" type="text" placeholder="Search by edition date"
                           name="search_book_author" value="">
                    <button type="submit" class="tfbutton">Search</button>
                </form>
            </div>

            <div class="search-container">
                <form name="" action="/" method="">
                    <button type="submit" class="tfbutton long_button">Top 10 books</button>
                </form>
            </div>
            <div class="search-container">
                <form name="" action="/" method="">
                    <button type="submit" class="tfbutton long_button">Top 10 unknown books</button>
                </form>
            </div>

        </div>

        <div id="result">
            <table id="result_table">
                <tr>
                    <th>
                        Author name
                    </th>
                    <th>
                        Book Title
                    </th>
                    <th>
                        Edition Date
                    </th>
                    <th>
                        Book Quantity
                    </th>
                </tr>

            </table>
        </div>
    </div>
</div>
</body>
</html>