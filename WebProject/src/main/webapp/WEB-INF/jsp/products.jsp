<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
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
    <link
            href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/business-casual.min.css" rel="stylesheet">

    <%--<!-- Bootstrap core JavaScript -->--%>
    <%--<script src="vendor/jquery/jquery.min.js"></script>--%>
    <%--<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>--%>

    <script src="js/ajax/shoppingCartAjax.js"></script>

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
                    <a class="nav-link text-uppercase text-expanded" href="cart">Cart <c:if
                            test="${allItemsQuantity > 0}">(${allItemsQuantity})</c:if></a>
                </li>
                <tag:login/>
                                <tag:I18N/>
            </ul>
        </div>
    </div>
</nav>

<h4 class="site-heading text-center text-white d-none d-lg-block">
    <span class="site-heading-upper">Available for buying: </span>
</h4>


<!-- PRODUCTS -->

<!-- style="position: absolute; top: 400; left: 0;" -->
<section class="page-section">
    <aside class="col-sm-3">
        <div class="card" style="float: left;">
            <form method="GET" action="products">
                <article class="card-group-item">
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapsePag" role="button"
                           aria-expanded="false" aria-controls="collapsePag">
                            <h5 class="title">Items quantity </h5>
                        </a>
                    </header>
                    <div>
                        <div class="filter-content collapse" id="collapsePag">
                            <div class="card-body">
                                <label class="form-check"> Enter item quantity which <br> will be showed on the page:
                                    <input class="form-control input-lg field" placeholder="Quantity of items"
                                           type="number" value="1" min="1" name="ItemsNumber">
                                    <input type="hidden" name="currentPage" value="1">
                                </label>
                            </div>
                        </div>
                    </div>
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseSearch" role="button"
                           aria-expanded="false" aria-controls="collapseSearch">
                            <h5 class="title">Search by name</h5>
                        </a>
                    </header>
                    <div>
                        <div class="filter-content collapse" id="collapseSearch">
                            <div class="card-body">
                                <label class="form-check"> Enter item name:
                                    <input class="form-control input-lg field" placeholder="Item name" type="search"
                                           value="" name="ItemName">
                                </label>
                            </div>
                        </div>
                    </div>
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseRange" role="button"
                           aria-expanded="false" aria-controls="collapseRange">
                            <h5 class="title">Price range</h5>
                        </a>
                    </header>
                    <div>
                        <div class="filter-content collapse" id="collapseRange">
                            <div class="card-body">
                                <label class="form-check"> From:
                                    <input class="form-control input-lg field" placeholder="From" type="number"
                                           value="" min="0" name="PriceFrom"> To:
                                    <input class="form-control input-lg field" placeholder="To" type="number" value=""
                                           min="0" name="PriceTo">
                                </label>
                            </div>
                        </div>
                    </div>
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseCat" role="button"
                           aria-expanded="false" aria-controls="collapseCat">
                            <h5 class="title">Categories </h5>
                        </a>
                    </header>
                    <div class="filter-content collapse" id="collapseCat">
                        <div class="card-body">
                            <c:forEach var="category" items="${categories}">
                                <label class="form-check">
                                    <input class="form-check-input" type="checkbox" value=${category}
                                            name="CategoryName">
                                    <span class="form-check-label">
                                            ${category}
                                    </span>
                                </label> <!-- form-check.// -->
                            </c:forEach>
                        </div> <!-- card-body.// -->
                    </div>
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseManuf" role="button"
                           aria-expanded="false" aria-controls="collapseManuf">
                            <h5 class="title">Manufacturers </h5>
                        </a>
                    </header>
                    <div class="filter-content collapse" id="collapseManuf">
                        <div class="card-body">
                            <c:forEach var="manuf" items="${manufacturers}">
                                <label class="form-check">
                                    <input class="form-check-input" type="checkbox" value="${manuf}"
                                           name="ManufName">
                                    <span class="form-check-label">
                                            ${manuf}
                                    </span>
                                </label> <!-- form-check.// -->
                            </c:forEach>
                        </div> <!-- card-body.// -->
                    </div>
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseSortPrice" role="button"
                           aria-expanded="false" aria-controls="collapseSortPrice">
                            <h5 class="title">Sort by price</h5>
                        </a>
                    </header>
                    <div>
                        <div class="filter-content collapse" id="collapseSortPrice">
                            <div class="card-body">
                                <label class="form-check">
                                    <input class="form-check-input" type="radio" name="sortPrice" value="ASC">
                                    <span class="form-check-label">
                                            ASC
                                        </span>
                                    <br>
                                    <input class="form-check-input" type="radio" name="sortPrice" value="DESC">
                                    <span class="form-check-label">
                                            DESC
                                        </span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <header class="card-header">
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseSortName" role="button"
                           aria-expanded="false" aria-controls="collapseSortName">
                            <h5 class="title">Sort by name</h5>
                        </a>
                    </header>
                    <div>
                        <div class="filter-content collapse" id="collapseSortName">
                            <div class="card-body">
                                <label class="form-check">
                                    <input class="form-check-input" type="radio" name="sortName" value="ASC">
                                    <span class="form-check-label">
                                            ASC
                                        </span>
                                    <br>
                                    <input class="form-check-input" type="radio" name="sortName" value="DESC">
                                    <span class="form-check-label">
                                            DESC
                                        </span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <input name="Apply" class="btn btn-lg btn-dark btn-block" value="Apply" type="submit">
                        </div>
                    </div>
                </article>
            </form>
        </div> <!-- card.// -->
    </aside> <!-- col.// -->

    <%--Pagination--%>
    <div class="container">
        <c:if test="${pageQuantity > 1}">
            <tag:pagination/>
        </c:if>
    </div>

    <%--Products--%>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="row">
                    <c:choose>
                        <c:when test="${empty products}">
                            <div style="position: absolute; top: 0px; left: 0; right: 10px;">
                                <h4 class="site-heading text-center text-danger d-none d-lg-block">
                                    <span class="site-heading-upper">No product(s) found! </span>
                                </h4>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="product" items="${products}" varStatus="count">
                                <div class="col-12 col-md-6 col-lg-4">
                                    <div class="card">
                                        <c:choose>
                                            <c:when test="${product.picture != null}">
                                                <img class="card-img-top productPhoto${count.index + 1}"
                                                     src="img/${product.picture}" width="120" height="250"
                                                     alt="Card image cap">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="card-img-top productPhoto${count.index + 1}"
                                                     src="img/default.png" width="120" height="250"
                                                     alt="Card image cap">
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="card-body">
                                            <h4 class="card-title productName${count.index + 1}"
                                                name="prodName">${product.name}</h4>
                                            <p style="margin-bottom: 5px" class="productManufacturer${count.index + 1}">
                                                <b>Brand:</b> ${product.manufacturer}</p>
                                            <p style="margin-bottom: 5px" class="productCategory${count.index + 1}"><b>Category:</b> ${product.category}
                                            </p>
                                            <p style="margin-bottom: 8px" class="productPrice${count.index + 1}"><b>Price:</b> ${product.price}
                                                $</p>
                                            <p style="margin-bottom: 8px" class="productDescription${count.index + 1}">
                                                <b>Description:</b> ${product.description}</p>
                                            <div class="row">
                                                <div class="col">
                                                    <button class="btn btn-success btn-block ${count.index + 1}"
                                                            value="${product.id}"
                                                            name="productId"
                                                            onclick="addToCart(this)">Add to Cart
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <%--Pagination--%>
    <div class="container">
        <c:if test="${pageQuantity gt 1}">
            <tag:pagination/>
        </c:if>
    </div>
</section>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>