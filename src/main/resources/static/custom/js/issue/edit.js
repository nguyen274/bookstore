if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}

function ready() {

    var inputLateDate = document.getElementById('date-late');
    inputLateDate.addEventListener("change", lateDate)

    var inputLostPage = document.getElementById('lost-page')
    inputLostPage.addEventListener("change", lostPage)

    var inputDirtyPage = document.getElementById('dirty-page');
    inputDirtyPage.addEventListener("change", dirtyPage)

    var inputWetPage = document.getElementById('water-page');
    inputWetPage.addEventListener("change", wetPage)

    var inputBrokenPage = document.getElementById('broken-page')
    inputBrokenPage.addEventListener("change", brokenPage)

    var inputTornPage = document.getElementById('left-page')
    inputTornPage.addEventListener("change", tornPage)

    var inputLostNote = document.getElementById('lost-note')
    inputLostNote.addEventListener('change', lostNote)

}

function lostPage(event){
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}
function dirtyPage(event){
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}

function wetPage(event){
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}
function brokenPage(event){
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}
function tornPage(event){
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}
function lateDate(event) {
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}

function lostNote(event){
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 0
    }
    updateTotalDepositPrice()
}

function updateTotalDepositPrice(){
    let lateDate = $('#date-late').val();
    let lostPage = $('#lost-page').val();
    let dirtyPage = $('#dirty-page').val();
    let brokenPage = $('#broken-page').val();
    let waterPage = $('#water-page').val();
    let leftPage = $('#left-page').val();
    let lostNote = $('#lost-note').val();
    let total = 0;

    let priceLateDate = lateDate * 5000;
    let priceLostPage = lostPage * 10000;
    let priceDirtyPage = dirtyPage * 3000;
    let priceBrokenPage = brokenPage * 15000;
    let priceWetPage = waterPage * 30000;
    let priceTornPage = leftPage * 50000;
    let priceLostNote = lostNote * 30000;

    total = priceLateDate + priceLostPage + priceDirtyPage + priceBrokenPage
            + priceWetPage + priceTornPage + priceLostNote;

    console.log(total)
    $('#total').text(new Intl.NumberFormat('de-DE').format(total))

}

$('#saveBtn').on('click', function(){

        var issue = {
            id: $('#id').val(),
            total: $('#total').text()
        }
        console.log(issue)
        $.get( "/rest/issue/save-fines-price", issue).done(function (data){
            if( data=='success' ) {
                window.location = '/issue/list';
            }
        });
});
