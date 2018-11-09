$(document).ready(function () {
    $("#form").on('submit', function (e) {
        e.preventDefault();
        var email = $('#emailField').val();
        var password = $('#passField').val();
        var dataFields = {
            "email": email,
            "password": password
        };
        $.ajax({
            type: 'Post',
            url: 'login',
            data: dataFields,
            success: function (result) {
                if (result.fieldError != null) {
                    alert(result.fieldError);
                }
                if (result.url != null) {
                    window.location.href = result.url;
                }
            },
            error: function (result) {
                alert("Something went wrong...");
            }
        })
    });
});