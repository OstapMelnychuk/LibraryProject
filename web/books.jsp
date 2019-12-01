<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <style type="text/css">

        #body_div{
            height: 100%;
            width: 100%;
            background-color: #989390;
        }
        #search_form {
            display: inline-block;
            font-family: verdana;
            height: 100%;
            width: 22%;
            background-color: #989390;

            vertical-align: bottom;
        }

        #result {
            display: inline-block;
            font-family: verdana;
            height: 100%;
            width: 77%;
            background-color: #989390;

            vertical-align: bottom;
        }

        #body_div {
            height: 100%;
            width: 100%;
            background-color: #989390;
        }


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

        .search-container {
            margin: 0;
            padding: 5px 5px;
        }

        .long_button {
            width: 98%;
        }

        #result_table {
            margin-top: 5px;
            width: 100%;
            color: white;
            text-align: right;
            visibility: hidden;
        }

        table {
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }
    </style>
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