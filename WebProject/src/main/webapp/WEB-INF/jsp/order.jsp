<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>All for Tennis</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link
            href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/business-casual.min.css" rel="stylesheet">

    <script src="js/ajax/shoppingCartAjax.js"></script>
</head>

<body>

<h1 class="site-heading text-center text-white d-none d-lg-block">
    <span class="site-heading-lower">All for Tennis</span>
</h1>

<script src="https://use.fontawesome.com/c560c025cf.js"></script>
<c:choose>
<c:when test="${not empty cart.getCart()}">
<form method="POST" action="orderHistory">
<nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#">All for Tennis</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="index.jsp">Back on Home page
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h4 class="site-heading text-center text-white d-none d-lg-block" id="cartHeader">
    <span class="site-heading-upper">Order: </span>
</h4>
<section class="page-section cta" id="cartSection">

    <div class="container" id="cartDiv">
        <div class="card shopping-cart">
            <%--<div class="card-header bg-dark text-light">--%>
            <%--<i class="fa fa-shopping-cart" aria-hidden="true"></i>--%>
            <%--Shopping cart--%>
            <%--<a href="products" class="btn btn-outline-info btn-sm"--%>
            <%--style="position: absolute; left: 15%;">Continue shopping</a>--%>
            <%--<button href="products" class="btn btn-danger btn-sm pull-right"--%>
            <%--onclick="clearCart()">Clear cart--%>
            <%--</button>--%>
            <%--<div class="clearfix"></div>--%>
            <%--</div>--%>
            <div class="card-body ${count.index + 1}">
                <!-- PRODUCT -->
                <c:forEach var="mapItem" items="${cart.getProducts()}" varStatus="count">
                    <div class="row" id="divEl${count.index + 1}" value="${mapItem.id}">
                        <div class="col-12 col-sm-12 col-md-2 text-center">
                            <c:choose>
                                <c:when test="${mapItem.picture != null}">
                                    <img class="img-responsive" src="img/${mapItem.picture}" alt="preview"
                                         width="120"
                                         height="80">
                                </c:when>
                                <c:otherwise>
                                    <img class="img-responsive" src="img/default.png" alt="preview" width="120"
                                         height="80">
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                            <h4 class="product-name"><strong>${mapItem.name}</strong></h4>
                            <h4>
                                <small>${mapItem.description}</small>
                            </h4>
                        </div>
                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                            <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                                <h6><strong>${mapItem.price}<span class="text-muted"> &nbsp;&nbsp;x</span></strong>
                                    &nbsp;&nbsp;${cart.getCart().get(mapItem)}</h6>
                            </div>
                                <%--<div class="col-4 col-sm-4 col-md-4">--%>
                                <%--<div class="quantity">--%>
                                <%--<input type="number" step="1" max="99" min="1"--%>
                                <%--id="itemQtyInput${count.index + 1}"--%>
                                <%--value="${cart.getCart().get(mapItem)}" title="Qty"--%>
                                <%--class="qty ${mapItem.id}"--%>
                                <%--size="4" name="ItemQuantity" onclick="changeItemQuantity(this)">--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="col-2 col-sm-2 col-md-2 text-right">--%>
                                <%--<button type="button" class="btn btn-outline-danger btn-xs ${count.index + 1}"--%>
                                <%--value="${mapItem.id}" onclick="deleteItemFromCart(this)">--%>
                                <%--<i class="fa fa-trash" aria-hidden="true"></i>--%>
                                <%--</button>--%>
                                <%--</div>--%>
                        </div>
                    </div>
                </c:forEach>
                <!-- END PRODUCT -->
            </div>

            <div class="card-footer">
                <div class="pull-left" style="margin: 10px">
                    <div class="pull-left" style="margin: 5px" id="a">
                        Type of payment: &nbsp;&nbsp;
                        <select class="custom-select-sm" id="payment" onchange="drawCardNumInput()" name="payType">
                            <option>Cash</option>
                            <option id="cardPay" value="Card">Card</option>
                        </select>
                    </div>
                </div>
                <div class="pull-right" style="margin: 10px">
                    Address: &nbsp;&nbsp; <input type="text" name="deliveryAddress" class="error-message" required>
                </div>
            </div>

            <div class="card-footer">
                <div class="pull-left" style="margin: 10px">

                    <div class="pull-left" style="margin: 5px" id="allItemsQuantity">
                        Products quantity: <b>${allItemsQuantity}</b>
                    </div>
                </div>
                <div class="pull-right" style="margin: 10px">
                    <%--<form method="GET" action="orderHistory" class="pull-right">--%>
                        <input type="hidden" value="${user}" id="sessionUser">
                        <input class="btn btn-success pull-right" value="Confirm Order" type="submit">
                    <%--</form>--%>
                    <div class="pull-right" style="margin: 5px">
                        Total cost: <b id="totalPrice">${totalPrice} $</b>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</form>
</c:when>
<c:otherwise>
<nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#">All for Tennis</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="index.jsp">Back on Home page
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
    <h4 class="site-heading text-center text-white d-none d-lg-block">
        <span class="site-heading-upper" style="color: red;">Impossible to checkout, because cart is empty! </span>
    </h4>
</c:otherwise>
</c:choose>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>