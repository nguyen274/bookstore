$(document).ready(function() {
    $('#saveBtn').on('click', function() {
        if (isValidateBirthDate()) {
            $('#staff-form').submit();
        } else {
            $('#dobErr').text('Invalid Date Format');
        }
    });

    function isValidateBirthDate() {
        var dateStr = $('#dateOfBirth').val();
        var timestamp = Date.parse(dateStr)
        return !isNaN(timestamp)
    }

    $('#gotoListBtn').on('click', function() {
        window.location = "/staff/list";
    });
});