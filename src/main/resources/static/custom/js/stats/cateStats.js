function cateChart(id, cateLabels=[], cateInfor=[]){
    const data = {
        labels: cateLabels,
        datasets: [{
            label: 'Thông kê số lượng sản theo thể loại Sách',
            data: cateInfor,
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)',
                'rgb(171,255,86)'
            ],
            hoverOffset: 4
        }]
    };
    var options = {
        maintainAspectRatio: false,
        scales: {
            y: {
                stacked: true,
                grid: {
                    display: true,
                    color: "rgba(255,99,132,0.2)"
                }
            },
            x: {
                grid: {
                    display: false
                }
            }
        }
    };
    const config = {
        type: 'doughnut',
        data: data,
    };

    const ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}