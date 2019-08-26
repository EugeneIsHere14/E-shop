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
    <span class="site-heading-lower"><fmt:message key = "header"/></span>
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
                    <a class="nav-link text-uppercase text-expanded" href="index.jsp"><fmt:message key = "home_link"/>
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="registration"><fmt:message key = "reg_link"/></a>
                </li>
                <li class="nav-item px-lg-4" id="prodLink">
                    <a class="nav-link text-uppercase text-expanded" href="products"><fmt:message key = "products_link"/></a>
                </li>
                <li class="nav-item px-lg-4" id="cartCount">
                                    <a class="nav-link text-uppercase text-expanded" href="cart" ><fmt:message key = "cart_link"/> <c:if test="${allItemsQuantity > 0}">(${allItemsQuantity})</c:if></a>
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
        <form class="row needs-validation" method="POST" enctype="multipart/form-data" novalidate action="registration">
            <div class="col-md-6 mx-auto">
                <div class="card card-body">
                    <h2 class="text-center mb-4" style="color: #0f6674; font-family: 'Comic Sans MS',sans-serif">
                        <fmt:message key = "sign_up"/></h2>
                    <c:if test="${not empty expireAlert}">
                        <div class="alert alert-danger" role="alert">
                            <p>${expireAlert}</p>
                        </div>
                    </c:if>
                    <fieldset>
                        <div class="form-group has-error">
                            <input class="form-control input-lg field" placeholder="<fmt:message key = "email"/>" name="E-mail"
                                   type="text"
                                   required id="email" value=${EmailValue}>
                        </div>
                        <div id="emailerror" class="errormsg">${emailerror}</div>
                        <div class="form-group has-error">
                            <input class="form-control input-lg field" placeholder="<fmt:message key = "login"/>" name="Login" type="text"
                                   required value=${LoginValue}>
                        </div>
                        <div id="loginerror" class="errormsg" style="color: red">${loginerror}</div>
                        <div id="loginmissmatch" class="errormsg" style="color: red">${LoginMismatch}</div>
                        <div class="form-group has-error">
                            <input class="form-control input-lg field" placeholder="<fmt:message key = "name"/>" name="Name" type="text"
                                   required value=${NameValue}>
                        </div>
                        <div id="nameerror" class="errormsg">${nameerror}</div>
                        <div class="form-group has-success">
                            <input class="form-control input-lg field" placeholder="<fmt:message key = "password"/>" name="Password" value=""
                                   type="password">
                        </div>
                        <div id="passworderror" class="errormsg">${passworderror}</div>
                        <div class="form-group has-success">
                            <input class="form-control input-lg field" placeholder="<fmt:message key = "conf_pass"/>"
                                   name="Confirm Password"
                                   type="password">
                        </div>
                        <div id="passwordmissmatch" class="errormsg" style="color: red">${PasswordMismatch}</div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" name="Terms of use"
                                       id="invalidCheck" required>
                                <label><fmt:message key = "terms"/></label>
                                <p><b><fmt:message key = "mailings"/></b></p>
                                <input type="checkbox" name="Subscription1" ${mailing1}>
                                <label><fmt:message key = "mailing1"/></label>
                                <br>
                                <input type="checkbox" name="Subscription2" ${mailing2}>
                                <label><fmt:message key = "mailing2"/></label>
                            </div>
                            <br><b><p><fmt:message key = "captcha"/></p></b>
                        </div>

                        <div><tag:captcha/></div>
                        <div style="color: red">${CaptchaError}</div>

                        <p><b><fmt:message key = "avatar"/></b></p>
                        <input type="file" style="width: 30% !important; display: block; margin: 0 auto;"
                               class="btn btn-secondary" name="NameOfFile"/>
                        <br>

                        <input name="Sign Up" class="btn btn-lg btn-primary btn-block" onclick="validate(this.form)"
                               value="<fmt:message key = "register"/>" type="button">
                        <%--<input name="Sign Up" class="btn btn-lg btn-primary btn-block"--%>
                        <%--value="Sign Me Up" type="submit">--%>
                    </fieldset>
                </div>
            </div>
        </form>
    </div>
</section>

<%--<footer class="footer text-faded text-center py-5">--%>
<%--<div class="container">--%>
<%--<p class="m-0 small">Copyright &copy; My Website 2019</p>--%>
<%--</div>--%>
<%--</footer>--%>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>