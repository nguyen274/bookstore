
$(document).ready(function() {
	$('#saveBtn').on('click', function() {
		if (isValidateBirthDate()) {
			$('#student-form').submit();
		} else {
			$('#dobErr').text('Invalid Date Format');
		}
	});

	function isValidateBirthDate() {
		var dateStr = $('#studentBirth').val();
		var timestamp = Date.parse(dateStr)
		return !isNaN(timestamp)
	}

	$('#gotoListBtn').on('click', function() {
		window.location = "/student/list";
	});
});