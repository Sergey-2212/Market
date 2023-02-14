angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/market/api/v1';

    $scope.loadProducts = function () {
        console.log("loadProducts")
        $http({
            url: contextPath + '/products/',
            method: 'GET',

        }).then(function (response) {
            console.log(response);
            $scope.ProductsList = response.data;
        })
    };

    $scope.loadCart = function () {
        console.log("loadCart");
        $http.get(contextPath + "/cart/")
            .then(function (response) {
                console.log(response.data);
                $scope.cart = response.data;
        })
    };

    $scope.addProductToCart = function (productId) {
        console.log("addProductToCart")
        $http({
            url: contextPath + "/cart/add/" + productId,
            method: 'GET',

            }).then(function (response) {
            console.log(response.data)
                $scope.loadCart();
            })
    };

    $scope.changeItemQuantity = function (productId, delta) {
        console.log("changeItemQuantity" + "productId - " + productId + ", delta - " + delta)
        $http({
            url: contextPath + "/cart/changequantity/",
            method: 'GET',
            params: {
                productId: productId,
                delta: delta,

            }
        }).then(function (response) {
        $scope.loadCart();
        })
    };

    $scope.deleteProductFromCart = function (productId) {
        $http({
            url: contextPath + "/cart/delete/item/",
            method: 'DELETE',
            params: {
                productId: productId,
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.loadCart();
        })
    };

    $scope.removeAllItemsFromCart = function () {
        $http.delete(contextPath + "/cart/delete/all/")
            .then(function (response) {
            console.log(response.data)
            $scope.loadCart();
        })
    }




    // $scope.loadFilteredProducts = function () {
    //     console.log("loadFilteredProducts")
    //     $http({
    //         url: contextPath + '/products/filter',
    //         method: 'POST',
    //         params: {
    //             min: $scope.productListFilter.min,
    //             max: $scope.productListFilter.max,
    //         }
    //     }).then(function (response) {
    //             console.log(response);
    //             $scope.ProductsList = response.data;
    //         })
    // };
/* Пример использования условий в JS
    $scope.currentPage = function () {
        if($scope.productListFilter.min === null && $scope.productListFilter.max === 0) {
            $scope.loadProducts();
        } else {
            $scope.loadFilteredProducts()
        }
    }

 */
    /* Чтобы функцию созданну в js можно было использовать в html, её нужно положить в scope*/
    $scope.deleteProduct = function (productId) {
       /* alert(studentId); /* выведем в алерте переданный studentId */
        $http({
            url: contextPath + '/products/',
            method: 'DELETE',
            params: {
                id: productId,
            }
        })
            .then(function (response) {
                alert('Deleted');
                $scope.loadProducts();  /* перегружаем страницу со списком продуктов */
            });
    }

    $scope.changePrice = function (studentId, delta) {
        /*Форма для создания шаблона get запроса с @RequestParam*/
        $http({
            url: contextPath + '/products/change_price/',
            method: 'PUT',
            params: {
                studentId: studentId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        })
    }

    $scope.createProductJSON = function () {
        $scope.newProductJSON.id = '1';
        console.log($scope.newProductJSON);

        $http.post(contextPath + '/products/', $scope.newProductJSON)
        // $http({
        //     url: contextPath + '/products/',
        //     method: 'POST',
        //     params: {
        //         id: $scope.newProductJSON.id,
        //         title: $scope.newProductJSON.title,
        //         price: $scope.newProductJSON.price,
        //     }
            .then(function(response) {
            alert("Продукт - " + response.data.title + " добавлен.")
            $scope.loadProducts();
        })
    }

    $scope.updateProduct = function () {
        console.log($scope.updateProductJSON);
        $http.put(contextPath + "/products/", $scope.updateProductJSON)
            .then(function (response) {
              console(response.data);
            alret("Продукт - " + $scope.id + " отредактирован");
        });
    }


    console.log("Перед загрузкой продукта")
    $scope.loadProducts();
    $scope.loadCart();
    console.log("End of html.")
});