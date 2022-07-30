var studentId, studentName;

function removeStudentDialog(el) {
	studentId = $(el).attr('data-student-id');
	studentName = $(el).attr('data-student-name');
	$('.remove-student-modal').find('#student-name').text(studentName);
}

function removeStudent() {
	$('.remove-student-modal').modal('hide');
	window.location = "/student/remove/" + studentId;
}