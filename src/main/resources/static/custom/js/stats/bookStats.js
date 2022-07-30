

function cateChart(id, cateLabels=[], cateInfor=[]) {
    const data = {
        labels: cateLabels,
        datasets: [{
            label: 'Doanh thu theo sách mượn',
            data: cateInfor,
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
    };
    const config = {
        type: 'line',
        data: data,
    };

    const ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}
$('#saveBtn').on('click', function(){

    var issue = {
        startDate: $('#startDate').val(),
        endDate: $('#endDate').val()
    }
    console.log(issue)
    $.get( "/book-price-stats", issue).done(function (data){
        if( data=='success' ) {
            window.location = '/book-price-stats';
        }
    });
});
