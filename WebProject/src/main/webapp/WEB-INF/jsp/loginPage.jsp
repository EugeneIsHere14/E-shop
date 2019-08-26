<!DOCTYPE html>
<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <!-- Registration form validation JQuery -->
    <script src="js/validation/util.js"></script>
    <script src="js/validation/validationJQuery.js"></script>
</head>

<body>

<h1 class="site-heading text-center text-white d-none d-lg-block">
    <span class="site-heading-lower">All For Tennis</span>
</h1>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#">All for Tennis</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav mx-auto" style="margin: 0 auto !important;">
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="index.jsp">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="registration">Registration</a>
                </li>
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

<!-- Registration Form -->
<section class="page-section cta">
    <div class="container-fluid bg-light py-3">
        <form class="row needs-validation" method="POST" action="login">
            <div class="col-md-6 mx-auto">
                <div class="card card-body">
                    <h2 class="text-center mb-4" style="color: #0f6674; font-family: 'Comic Sans MS',sans-serif">
                        Login</h2>
                    <fieldset>
                        <div class="form-group has-error">
                            <input class="form-control input-lg field" placeholder="Login" name="LoginUp"
                                   type="text" value="${LoginUpValue}">
                            <div class="errormsg" style="color: red">${FalseLogin}</div>
                        </div>
                        <div class="form-group has-error">
                            <input class="form-control input-lg field" placeholder="Password" name="PasswordUp" type="password">
                            <div class="errormsg" style="color: red">${FalsePassword}</div>
                        </div>
                        <br>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                    </fieldset>
                </div>
            </div>
        </form>
    </div>
</section>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>