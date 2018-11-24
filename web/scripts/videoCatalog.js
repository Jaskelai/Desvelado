$(document).ready(function () {
    $("#btnUpload").on('submit', function (e) {
        e.preventDefault();
        var link = $('#linkInput').val();
        var header = $('#headerInput').val();
        var description = $('#descriptionInput').val();
        var dataFields = {
            "link": link,
            "header": header,
            "description": description
        };
        $.ajax({
            type: 'Post',
            url: 'addcall',
            data: dataFields,
            success: function (result) {

            },
            error: function (result) {
                alert("Something went wrong with server...");
            }
        })
    });
});