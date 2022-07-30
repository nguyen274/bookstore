var courseId, courseName;

function removeCourseDialog(el) {
	courseId = $(el).attr('data-course-id');
	courseName = $(el).attr('data-course-name');
	$('.remove-course-modal').find('#course-name').text(courseName);
}

function removeCourse() {
	$('.remove-course-modal').modal('hide');
	window.location = "/course/remove/" + courseId;
}