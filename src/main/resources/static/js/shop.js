$(document).ready(function () {

    // for menu of categories
    $(".widget-title_2").click(function () {
        const collapse = $(($(this).data("target")));
        const content = collapse.find(".sub_menu");

        console.log(collapse.get(0).getAttribute('aria-expanded'));

        if (collapse.get(0).getAttribute('aria-expanded') === "true") {
            content.get(0).style.maxHeight = 0;
            collapse.get(0).setAttribute('aria-expanded', false);
        } else {
            content.get(0).style.maxHeight = 400 + 'px';
            collapse.get(0).setAttribute('aria-expanded', true);
        }

        return false;
    });


    // for favorite products
    $("#product-favourite").click(function () {

        $.ajax({
            url	: 'http://localhost:8080/shop/add_favorite_product',
            type: 'POST',
            data: {
                _csrf		: $(this).children('input[name="_csrf"]').val(),
                product_id	: $(this).children('input[name="product_id"]').val(),
            },
            success: function (msg) {
                console.log('Email Sent');
            }
        });

        return false;
    });
});
