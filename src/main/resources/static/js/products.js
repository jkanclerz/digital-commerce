var ProductComponent = function(data) {
   var template = [
       '<li>',
            '<span>__name__</span>',
            '<img src="__image__"/>',
       '</li>',
   ];

   return {
        render: function() {
            var tmp = template.join("");
            tmp = tmp.replace("__name__", data.name);
            tmp = tmp.replace("__image__", data.image);

            return tmp;
        }
   }
}

document.addEventListener("DOMContentLoaded", function() {
    var productList = document.getElementsByClassName("products")[0];
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
            ;

        })
        .catch(function(e){
            "Sth....";
        })
    ;
});