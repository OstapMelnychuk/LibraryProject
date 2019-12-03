<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Bootstrap Case</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css/addBook.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="form-add">
   <div class="form-add-book">
       <form method="post" action="/">
           <div class="form-group">
               <label for="exampleFormControlInput1">Author's name</label>
               <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="name">
           </div>
           <div class="form-group">
               <label for="exampleFormControlInput1">Title</label>
               <input type="text" class="form-control" id="exampleFormControlInput2" placeholder="title">
           </div>
           <div class="form-group">
               <label for="exampleFormControlTextarea1">Book description</label>
               <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
           </div>
           <div class="form-group">
               <label for="exampleFormControlInput1">Edition date</label>
               <input type="date" class="form-control" id="exampleFormControlInput3" placeholder="2019-10-10">
           </div>
           <div class="form-group">
               <label for="exampleFormControlInput1">Quantity</label>
               <input type="text" class="form-control" id="exampleFormControlInput4" placeholder="quantity">
           </div>
           <button type="submit" class="btn btn-primary mb-2">Search</button>
       </form>
   </div>
</div>

</body>
</html>

