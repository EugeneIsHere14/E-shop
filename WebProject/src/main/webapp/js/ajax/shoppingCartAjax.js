function addToCart(product) {
    var productId = $(product).val();

    $.ajax({
        url: "cart",
        data: {
            prodId: productId
        },
        type: 'POST',
        success: function (data) {
            var value = JSON.parse(data);
            $("#cartCount").remove();
            $("#prodLink").after('<li class="nav-item px-lg-4" id="cartCount"><a class="nav-link text-uppercase text-expanded" href="cart">Cart (' + value.allItemsQuantity + ')</a></li>');
        }
    });
}

function deleteItemFromCart(product) {
    var productId = $(product).val();
    var itemClass = $(product).attr("class").split(' ')[3];
    $.ajax({
        url: "deleteFromCart",
        data: {
            prodId: productId
        },
        type: 'POST',
        success: function (data) {
            var value = JSON.parse(data);
            $("#divEl" + itemClass).remove();
            $("#allItemsQuantity").text(value.allItemsQuantity);
            $("#totalPrice").text(value.totalPrice + ".00 $");

            $("#cartCount").remove();
            $("#prodLink").after('<li class="nav-item px-lg-4" id="cartCount"><a class="nav-link text-uppercase text-expanded" href="cart">Cart (' + value.allItemsQuantity + ')</a></li>');

            if (value.totalPrice === 0) {
                $("#cartCount").remove();
                $("#prodLink").after('<li class="nav-item px-lg-4" id="cartCount"><a class="nav-link text-uppercase text-expanded" href="cart">Cart</a></li>');
                $("#cartSection").remove();
                $("#cartHeader").remove();
                $("#mainNav").after('<h4 class="site-heading text-center text-white d-none d-lg-block" id="emptyCartMsg"><span class="site-heading-upper">No items in the shopping cart </span></h4>');
            }
        }
    });
}

function clearCart() {
    $.ajax({
        url: "clearCart",
        type: 'POST',
        success: function () {
            $("#cartCount").remove();
            $("#prodLink").after('<li class="nav-item px-lg-4" id="cartCount"><a class="nav-link text-uppercase text-expanded" href="cart">Cart</a></li>');
            $("#cartSection").remove();
            $("#cartHeader").remove();
            $("#mainNav").after('<h4 class="site-heading text-center text-white d-none d-lg-block" id="emptyCartMsg"><span class="site-heading-upper">No items in the shopping cart </span></h4>');
        }
    });
}

function changeItemQuantity(product) {
    var productQuantity = $(product).val();
    var productId = $(product).attr("class").split(' ')[1];
    $.ajax({
        url: "changeItemQuantity",
        data: {
            prodId: productId, ItemQuantity: productQuantity
        },
        type: 'POST',
        success: function (data) {
            var value = JSON.parse(data);
            $("#allItemsQuantity").text(value.allItemsQuantity);
            $("#totalPrice").text(value.totalPrice + ".00 $");

            $("#cartCount").remove();
            $("#prodLink").after('<li class="nav-item px-lg-4" id="cartCount"><a class="nav-link text-uppercase text-expanded" href="cart">Cart (' + value.allItemsQuantity + ')</a></li>');
        }
    });
}

function checkUserAuthentication(button) {
    var user = $("#sessionUser").val();
    if (user.length === 0) {
        alert("For buying items, please, login.");
        return false;
    } else {
        button.submit();
    }
}

function drawCardNumInput() {
    $.ajax({
        url: "order",
        type: 'GET',
        success: function () {
            var select = $("#payment :selected").text();
            if (select === "Card") {
                $("#payInput").remove();
                $("#payment").after('<p id="payInput"><br>Card number: &nbsp;&nbsp;<input class="error-message" name="cardNumber" required pattern="^(\\d{4}[- ]){3}\\d{4}|\\d{16}$"></p>');
            } else {
                $("#payInput").remove();
            }
        }
    });
}