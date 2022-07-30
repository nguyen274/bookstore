var majorId, majorName;

function removeMajorDialog(el) {
	majorId = $(el).attr('data-major-id');
	majorName = $(el).attr('data-major-name');
	$('.remove-major-modal').find('#major-name').text(majorName);
}

function removeMajor() {
	$('.remove-major-modal').modal('hide');
	window.location = "/major/remove/" + majorId;
}