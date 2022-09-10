let book = {}

//get book by id book
function getBookByBookCode() {

}

$('#addBook').on('click', function () {
    // Get book
    book = {}
    var bookCode = $('#bookCode').val();
    var amount = $("#amount").val();
    $.ajax({
        async: false,
        url : `/rest/issue/getBook/${bookCode}`,
        type : "GET",
        success : res => {
            if (res) {
                if(res.amount < amount){
                    Swal.fire({
                        icon: 'info',                        title: 'Thông báo',
                        text: 'Số lượng hiện tại không đủ',
                    })
                }else {
                    book["id"] = res.id
                    book["bookCode"] = res.bookCode
                    book["bookName"] = res.bookName
                    book["price"] = new Intl.NumberFormat('de-DE').format(res.price)
                }
            }
        }
    });

    // Set table
    //const amount = $("#amount").val()

    if (validateInput(amount)) {
        book['amount'] = amount
        const temp = booksToIssue.filter(p => p.bookCode === book.bookCode);
        if (temp.length !== 0) {
            booksToIssue.map((value, index) => {
                if (value.bookCode === temp[0].bookCode) {
                    value.amount = +value.amount + +book.amount;
                }
            })
        } else {
            booksToIssue.push(book);
        }
        // $('#booksSel').val('');
        initBooksInTable()
    }
});

function validateInput(amount) {
    if ($.isEmptyObject(book)) {
        Swal.fire({
            icon: 'info',
            title: 'Khong tim thay sach',
            text: '',
        })
        return false;
    } else if (amount === '') {
        Swal.fire({
            icon: 'info',
            title: 'Thong bao',
            text: 'Vui long nhap so luong!',
        })
        return false;
    }
    return true;
}

var members = [];

function initMembers() {
    $.get('/rest/member/list', function (data) {
        if (data) {
            members = data;
        }
    });
}

initMembers();

function getMembersByType(memberType) {
    var filteredMembers = [];
    for (var k = 0; k < members.length; k++) {
        if (members[k].type == memberType) {
            filteredMembers.push(members[k]);
        }
    }
    return filteredMembers;
}

function populateMembersList(membersList) {
    $('#memberSel').empty().append('<option selected="selected" value="">-- Select Member --</option>');
    $.each(membersList, function (k, v) {
        $('#memberSel').append($("<option></option>")
            .attr("value", v.id).text(v.name));
    });
}

$('#memberTypeSel').on('change', function () {
    var value = $(this).val();
    if (value) {
        var fiteredMembers = getMembersByType(value);
        populateMembersList(fiteredMembers);
    } else {
        populateMembersList([]);
    }
});

// action of book category
function getBooksByCategory(value) {
    $.get('/rest/book/' + value + '/available', function (data) {
        if (data) {
            populateBooksList(data);
        }
    });
}

function populateBooksList(booksList) {
    $('#booksSel').empty().append('<option selected="selected" value="">-- Select Book --</option>');
    $.each(booksList, function (k, v) {
        $('#booksSel').append($("<option></option>")
            .attr("value", v.id).text(v.bookName)
            .attr("data-price", v.price)
            .attr("data-deposit-price", v.depositPrice)
            .attr("data-tag", v.bookCode));
    });
}

$('#categorySel').on('change', function () {
    var value = $(this).val();
    if (value) {
        var books = getBooksByCategory(value);
    } else {
        populateBooksList([]);
    }
});


function bookAlreadyExist(id) {
    for (var k = 0; k < booksToIssue.length; k++) {
        if (booksToIssue[k].id == id) {
            return true;
        }
    }
    return false;
}

$('#saveBtn').on('click', function () {
    var errors = validate();
    if (errors.length > 0) {
        $('.errors-modal').find('.modal-body').html(errors.join('<br />'));
        $('.errors-modal').modal('show');
    } else {
        var issue = {
            member: $('#memberSel').val(),
            totalDepositPrice: $('#totalDepositPrice').text(),
            amount: getAmountByBook().join(),
            total: $('#total').text(),
            books: getIssuedBookIds().join()
        }
        $.get("/rest/issue/save", issue).done(function (data) {
            if (data == 'success') {
                window.location = '/issue/new';
            }
        });
    }
});

function getIssuedBookIds() {
    var ids = [];
    for (var k = 0; k < booksToIssue.length; k++) {
        ids.push(booksToIssue[k].id);
    }
    return ids;
}

function getAmountByBook() {
    var amountByBook = [];
    for (var k = 0; k < booksToIssue.length; k++) {
        amountByBook.push(booksToIssue[k].amount);
    }
    return amountByBook;
}

function validate() {
    var errors = []
    var member = $('#memberSel').val();
    if (!member) {
        errors.push('- Select Member');
    }
    if (booksToIssue.length == 0) {
        errors.push('- Add Books to issue');
    }
    return errors;
}

var booksToIssue = [];
console.log(booksToIssue);

function initBooksInTable() {
    console.log('here', booksToIssue.length);
    var trs = '';
    var total = 0;
    var totalBook = 0;
    var totalDepositPrice = 0;

    for (var k = 0; k < booksToIssue.length; k++) {
        console.log(booksToIssue[k].title)
        console.log(booksToIssue[k].amount)
        var rowNum = k + 1;
        trs += '<tr>';
        trs += '<td>' + rowNum + '</td>';
        trs += '<td>' + booksToIssue[k].bookCode + '</td>';
        trs += '<td>' + booksToIssue[k].bookName + '</td>';
        trs += '<td>' + booksToIssue[k].amount + '</td>';
        trs += '<td>' + booksToIssue[k].price + '</td>';
        trs += '<td>'+new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'VND' }).format(booksToIssue[k].amount*booksToIssue[k].price * 1000)+'</td>';
        trs += '<td><a href="javascript:void(0)"  onclick="removeFromTable(' + rowNum + ', ' + booksToIssue[k].id + ')"><i class="fa fa-remove"></i></a></td>';
        trs += '</tr>';

        totalBook = parseFloat(booksToIssue[k].price) * parseFloat(booksToIssue[k].amount * 1000);
        total += totalBook;
        totalDepositPrice += parseFloat(booksToIssue[k].depositPrice);


    }
    console.log(total);
    $("#issueBooksTable").find("tr:gt(0)").remove();
    $('#issueBooksTable').append(trs);
    $('#total').text(new Intl.NumberFormat('de-DE').format(total));
    $('#totalDepositPrice').text(new Intl.NumberFormat('de-DE').format(total));


}

function removeFromTable(rowNum, id) {
    $('#issueBooksTable tr:eq(' + (rowNum) + ')').remove();
    removeFromBooksIssuedList(id);
    initBooksInTable();
}

function removeFromBooksIssuedList(id) {
    for (var k = 0; k < booksToIssue.length; k++) {
        if (booksToIssue[k].id == id) {
            booksToIssue.splice(k, 1);
            break;
        }
    }
}