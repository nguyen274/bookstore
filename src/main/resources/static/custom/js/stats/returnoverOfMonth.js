function cateChart(id, cateLabels=[], cateInfor=[]) {
    const data = {
        labels: cateLabels,
        datasets: [{
            label: 'Lợi Nhuận',
            data: cateInfor,
            fill: false,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)'],
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
    };
    const config = {
        type: 'bar',
        data: data,
    };

    const ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}