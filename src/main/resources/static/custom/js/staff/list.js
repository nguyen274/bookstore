var producerId, producerName;

function removeProducerDialog(el) {
	producerId = $(el).attr('data-producer-id');
	producerName = $(el).attr('data-producer-name');
	$('.remove-producer-modal').find('#producer-name').text(producerName);
}

function removeProducer() {
	$('.remove-producer-modal').modal('hide');
	window.location = "/producer/remove/" + producerId;
}