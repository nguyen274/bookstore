$(document).ready(function() {
	$('#gotoListBtn').on('click', function() {
		window.location = "/book/list";
	});
	
	$('#category-selectbox').on('change', function() {
		updateBookCodeField();
	});
	
	$('#resetBtn').on('click', function() {
		setTimeout(function(){
			updateBookCodeField();
		}, 10);
		
	});
	
	function updateBookCodeField() {
		var bookCategoryCode =  $("#category-selectbox option:selected").attr("book-category-code");
		if( bookCategoryCode ) {
			$('#bookCode').val( bookCategoryCode + '-' );
		}
	}
	
	if( !$('#id').val() ) {
		updateBookCodeField();
	}
});