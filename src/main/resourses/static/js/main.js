$(document).ready(function() {
    $('.infoBtn').on('click', function(event) {
		var id = $(this).attr('entryIndex');
		console.log(id);
        // location.reload(false);
	});

    $('.removeBtn').on('click', function(event) {
		var id = $(this).attr('entryIndex');
		$.get("/products/remove/" + id);
        // reload();
	});

    function reload() {
        document.location.reload(true);
    }

	$('#consoleTestBtn').on('click', function(event) {
		console.log($('#myInput').val());
	});

	$('.table .editBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');		
		$.get(href, function(book, status) {
			$('.myForm #id').val(products.id);
			$('.myForm #item').val(products.item);
			$('.myForm #title').val(book.title);
		});		
		$('.myForm #editModal').modal();
	});
	
	$('.addNewProductBtn').on('click', function(event) {
		event.preventDefault();		
		var href = $(this).attr('href');		
		$.get(href, function(products, status) {
		$('.myForm #id').val(products.id);
        $('.myForm #item').val(products.item);
        $('.myForm #title').val(book.title);

		});	
		$('.myForm #editModal').modal();
	});


});