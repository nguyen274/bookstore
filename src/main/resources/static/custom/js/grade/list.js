var grade, gradeName;

function removeGradeDialog(el) {
	gradeId = $(el).attr('data-grade-id');
	gradeName = $(el).attr('data-grade-name');
	$('.remove-grade-modal').find('#grade-name').text(gradeName);
}

function removeGrade() {
	$('.remove-grade-modal').modal('hide');
	window.location = "/grade/remove/" + gradeId;
}