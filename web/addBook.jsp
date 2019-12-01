<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <link href="resources/css/addBook.css" rel="stylesheet" type="text/css">
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