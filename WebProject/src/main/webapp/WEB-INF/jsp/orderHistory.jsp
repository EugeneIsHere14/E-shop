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

<h4 class="site-heading text-center text-white d-none d-lg-block" id="cartHeader">
    <span class="site-heading-upper">Order summary: </span>
</h4>

<script src="https://use.fontawesome.com/c560c025cf.js"></script>
<section class="page-section cta" id="cartSection">

    <div class="container" id="cartDiv">
        <div class="card shopping-cart">
            <div class="card-body ${count.index + 1}">
                <!-- PRODUCT -->

                <div class="card-header bg-dark text-light">
                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                    Order summary
                    <form method="GET" action="products" class="pull-right">
                        <input type="hidden" value="${user}" id="sessionUser">
                        <input class="btn btn-success pull-right" value="Back to Store" type="submit">
                    </form>
                    <div class="clearfix"></div>
                </div>

                <c:forEach var="mapItem" items="${orderItems}" varStatus="count">
                    <div class="row" id="divEl${count.index + 1}" value="${mapItem.orderId}">
                        <div class="col-12 col-sm-12 col-md-2 text-center">
                            <c:choose>
                                <c:when test="${mapItem.productPicture != null}">
                                    <img class="img-responsive" src="img/${mapItem.productPicture}" alt="preview"
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
                            <h4 class="product-name"><strong>${mapItem.productName} XX</strong></h4>
                        </div>
                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                            <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                                <h6><strong>${mapItem.productPrice}<span class="text-muted"> &nbsp;&nbsp;x</span></strong>
                                    &nbsp;&nbsp;${mapItem.productQuantity}</h6>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!-- END PRODUCT -->
            </div>

            <div class="card-footer">
                <div class="pull-left" style="margin: 10px">
                    <div class="pull-left" style="margin: 5px ; font-size: 22px" id="allItemsQuantity">
                        Products quantity: <b>${totalOrderItemQty}</b>
                    </div>
                </div>
                <div class="pull-right" style="margin: 10px; font-size: 22px">
                    <div class="pull-right" style="margin: 5px">
                        Total cost: <b id="totalPrice">${totalOrderCost} $</b>
                    </div>
                </div>

            </div>
            <div class="card-footer">
                <c:if test="${not empty cardNumber}"><div class="row pull-left" style="font-size: 22px; margin: 5px">
                    Card Number:&nbsp;&nbsp; <b>${cardNumber}</b>
                </div></c:if>
                <div class="row pull-right" style="font-size: 22px">
                    Delivery Address:&nbsp;&nbsp; <b> ${deliveryAddress}</b>
                </div>
            </div>

            <div class="card-footer">
                <div class="row pull-left" style="font-size: 22px; margin: 5px">
                    Order Status:&nbsp;&nbsp; <b>${status}</b>
                </div>
                <div class="row pull-right" style="font-size: 22px">
                    Order Description:&nbsp;&nbsp; <b> ${statusDescription}</b>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>