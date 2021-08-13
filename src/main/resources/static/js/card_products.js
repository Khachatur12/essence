$(document).ready(function(){

    $(".card_img").hover(
        function(){
            $(this).children(".card_second_img").css("opacity", "0");
            $(this).children("#product-favourite").css("opacity", "1");
            $(this).children(".add-to-cart__btn").css("opacity", "1");
        },
        function(){
            $(this).children(".card_second_img").css("opacity", "1");
            $(this).children("#product-favourite").css("opacity", "0");
            $(this).children(".add-to-cart__btn").css("opacity", "0");
        }
    );

});