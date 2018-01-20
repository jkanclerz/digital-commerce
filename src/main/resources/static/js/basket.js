var BasketComponent = function(data) {
   var template = [
       '<div class="basket__info float-right">',
           '<span class="count">__items_count__ </span>',
           ' | ',
           '<span class="subtotal"> __total__ __currency__</span>',
           '<div class="dropdown-menu">',
               '<div class="dropdown-item">',
                 '<a class="btn btn-sm btn-success" href="#">Checkout</a>',
               '</div>',
             '</div>',
       '</div>',
   ];

   return {
        render: function() {
            var tmp = template.join("");
            tmp = tmp.replace("__items_count__", data.itemsCount);
            tmp = tmp.replace("__total__", data.total);
            tmp = tmp.replace("__currency__", data.currency);

            return tmp;
        }
   }
}

var loadBasketInformation = function(basketContainer) {
    axios.get('/offer')
        .then(function(response) {
            basketContainer.innerHTML = (new BasketComponent(response.data)).render();
        })
    ;
}

var basketContainer = document.getElementsByClassName("basket")[0];

document.addEventListener("DOMContentLoaded", function() {
    basketContainer.innerHTML = (new BasketComponent({
        itemsCount: 0,
        total: 0,
        currency: "PLN"
    })).render();
    loadBasketInformation(basketContainer);
});

document.addEventListener('item_placed_in_basket', function(e) {
    loadBasketInformation(basketContainer);
});

document.addEventListener("DOMContentLoaded", function() {
    console.log('dadassad');
    $(document).on("hover", '.basket__info', function() {
      console.log('adsdsadsa');
      $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
    }, function() {
      $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
    });
});