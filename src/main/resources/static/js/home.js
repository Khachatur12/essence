$(document).ready(function(){
	
	// for popular products slide
	var card_size = 280;
	var time = 5000;
	var differencePX = 260;
	var pp_slides = $("#popular-products-slide__content");
	var pp_slides_content_n1 = $("#popular-products-slide__content-n1");
	var pp_slides_content_n2 = $("#popular-products-slide__content-n2");
	var pp_transition = pp_slides.css("transition");

	function popular_products_slide(){
		pp_slides.css("transition", pp_transition);
		var position = parseInt(pp_slides.css("left"));
		var newPosition = position - 280;
		pp_slides.css("left", newPosition);

		if (position < -2*card_size+10) {
			pp_slides.css("transition", "non");
			newPosition = 4*card_size;

			if( pp_slides_content_n1.css("float") == "left" ){
				pp_slides_content_n1.css("float", "right");
				pp_slides_content_n2.css("float", "left");
			} else{
				pp_slides_content_n1.css("float", "left");
				pp_slides_content_n2.css("float", "right");
			}
			pp_slides.css("left", newPosition);
			setTimeout(popular_products_slide, 100);
			return 0;
		}
		setTimeout(popular_products_slide, time);
	}
	
	popular_products_slide();
});
