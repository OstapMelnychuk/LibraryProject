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

        .form_input_width {
            width: 100%;
        }

        #add_book_body_div {
            width: 100%;
            text-align: center;
            background-color: #989390;

        }

        #add_book_block {
            display: inline-block;
            width: 40%;
            background-color: #989390;

        }

        .add_book_container {
            width: 100%;
            padding: 5px 5px;
            display: block;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div id="main_div">
    <jsp:include page="navbar.jsp"/>
    <div id="add_book_body_div">
        <div id="add_book_block">
            <form name="" action="/" method="">
                <div class="add_book_container">
                    <input class="tftextinput form_input_width" type="text" placeholder="Author" name="book_author"
                           value="">
                </div>
                <div class="add_book_container">
                    <input class="tftextinput form_input_width" cols="50" type="text" placeholder="Title"
                           name="book_title" value="">
                </div>
                <div class="add_book_container">
                    <textarea class="tftextinput form_input_width" rows="10" cols="50" name="book_description"
                              placeholder="Book description"></textarea>

                </div>
                <div class="add_book_container">
                    <input class="tftextinput form_input_width" type="text"
                           placeholder="Edition date format for example 2019-09-31" name="date_of_publishment" value="">
                </div>
                <div class="add_book_container">
                    <input class="tftextinput form_input_width" type="text" placeholder="Quantity" name="quantity"
                           value="">
                </div>
                <div class="add_book_container">
                    <button type="submit" class="tfbutton login_button">Add the book</button>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>