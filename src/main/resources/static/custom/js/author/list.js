var authorId, authorName;

function removeAuthorDialog(el) {
    authorId = $(el).attr('data-author-id');
    authorName = $(el).attr('data-author-name');
    $('.remove-author-modal').find('#author-name').text(authorName);
}

function removeAuthor() {
    $('.remove-author-modal').modal('hide');
    window.location = "/author/remove/" + authorId;
}