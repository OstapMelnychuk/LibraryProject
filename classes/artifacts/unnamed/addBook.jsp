<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css/addBook.css">
    <script src="resources/js/jQuery.js"></script>
    <script src="resources/js/validator.min.js" type="javascript"></script>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="form-add">
    <div class="form-add-book">

        <form method="post" action="/addBook" id="add_book">
            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlInput1">Author's name</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="name" placeholder="name"
                       required>
            </div>

            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlInput1.2">Author's second name</label>
                <input type="text" class="form-control" id="exampleFormControlInput1.2" name="secondname"
                       placeholder="second name" required>
            </div>
            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlInput1.3">Author's surname</label>
                <input type="text" class="form-control" id="exampleFormControlInput1.3" name="surname"
                       placeholder="surname" required>
            </div>
            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlInput1">Title</label>
                <input type="text" class="form-control" id="exampleFormControlInput2" name="title" placeholder="title"
                       required>
            </div>
            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlTextarea1">Book description</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" name="description" rows="3"
                          required></textarea>
            </div>
            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlInput1">Edition date</label>
                <input type="date" class="form-control" id="exampleFormControlInput3" name="date"
                       placeholder="2019-10-10" required>
            </div>
            <div class="form-group needs-validation" novalide>
                <label for="exampleFormControlInput1">Quantity</label>
                <input type="text" class="form-control" id="exampleFormControlInput4" name="quantity"
                       placeholder="quantity" required>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Add book</button>
        </form>
    </div>
</div>

<div style="color: gold; margin-left: 700px; margin-top: -600px;">
    <h2>${message}</h2>
</div>
</body>
</html>

