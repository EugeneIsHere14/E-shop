<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="${pageContext.request.locale}"/>
    <fmt:setBundle basename="localization"/>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>All for Tennis</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/business-casual.min.css" rel="stylesheet">

</head>

<body>

<h1 class="site-heading text-center text-white d-none d-lg-block">
    <span class="site-heading-lower">All for Tennis</span>
</h1>

<!-- Navigation -->
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
                    <a class="nav-link text-uppercase text-expanded" href="index.jsp">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <c:if test="${empty user}">
                    <li class="nav-item px-lg-4">
                        <a class="nav-link text-uppercase text-expanded" href="registration">Registration</a>
                    </li>
                </c:if>
                <li class="nav-item px-lg-4" id="prodLink">
                    <a class="nav-link text-uppercase text-expanded" href="products">Products</a>
                </li>
                <li class="nav-item px-lg-4" id="cartCount">
                    <a class="nav-link text-uppercase text-expanded" href="cart" >Cart <c:if test="${allItemsQuantity > 0}">(${allItemsQuantity})</c:if></a>
                </li>
                <tag:login/>
                <tag:I18N/>
            </ul>
        </div>

    </div>
</nav>

<section class="page-section clearfix">
    <div class="container">
        <div class="intro">
            <img class="intro-img img-fluid mb-3 mb-lg-0 rounded" src="img/intro.jpeg" alt="">
            <div class="intro-text left-0 text-center bg-faded p-5 rounded">
                <h2 class="section-heading mb-4">
                    <span class="section-heading-upper">Tennis Goods</span>
                    <span class="section-heading-lower">Worth Buying</span>
                </h2>
                <p class="mb-3">We provide only high quality products at attractive prices. We are now five years on the
                    market.
                    Our online assistants have a rich experience in tennis sphere and always to help with advice.</p>
                <div class="intro-button mx-auto">
                    <a class="btn btn-primary btn-xl" href="#">Visit Us Today!</a>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="page-section cta">
    <div class="container">
        <div class="row">
            <div class="col-xl-9 mx-auto">
                <div class="cta-inner text-center rounded">
                    <h2 class="section-heading mb-4">
                        <span class="section-heading-upper">Our Promise</span>
                        <span class="section-heading-lower">To You</span>
                    </h2>
                    <p class="mb-0">Although we only high quality goods, on every product we give you at least two week
                        garanty.
                        In addition you can try a racket testdrive before and decide whether you want to buy it or not.
                        <br>
                        So order something and make sure our promises are honest!</p>
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