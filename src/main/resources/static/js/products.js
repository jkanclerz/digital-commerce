var ProductComponent = function(data) {
   var template = [
       '<div class="product">',
           '<div class="row">',
               '<div class="col-sm-2"></div>',
               '<div class="col-sm-4">',
                   '<h4>__name__</h4>',
                   '<p class="lead">__description__</p>',
                   '<hr class="my-4">',
                   '<div class="lead">',
                        '<span>',
                            '__price__ PLN',
                        '</span>',
                       '<a class="product__add-to-basket btn btn-primary btn-sm float-right" href="#" role="button">Add to basket</a>',
                   '</div>',
               '</div>',
               '<div class="col-sm-4 product__image">',
                   '<img src="__image__"/>',
               '</div>',
           '</div>',
       '</div>',
   ];

   return {
        render: function() {
            var tmp = template.join("");
            tmp = tmp.replace("__name__", data.name);
            tmp = tmp.replace("__image__", data.image);
            tmp = tmp.replace("__price__", data.price);
            tmp = tmp.replace("__description__", data.description);

            return tmp;
        }
   }
}

var initializeCarousel = function() {
    $(".owl-carousel").owlCarousel({
        center:true,
        loop:true,
        margin:10,
        items:1,
        autoplay: true
    });
}

document.addEventListener("DOMContentLoaded", function() {
    var productList = document.getElementsByClassName("products__list")[0];
    axios.get("/products")
        .then(function (response) {
            response.data
                .map(function(data){
                    return new ProductComponent(data);
                })
                .map(function(productComponent) {
                    return productComponent.render();
                })
                .forEach(function(html) {
                    productList.insertAdjacentHTML("beforeend", html);
                })

                initializeCarousel();
            ;

        })
        .catch(function(e){
            "Sth....";
        })
    ;
});