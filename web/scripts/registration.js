$(document).ready(function () {
    $("#form").on('submit', function (e) {
        e.preventDefault();
        var email = $('#emailField').val();
        var username = $('#usernameField').val();
        var password = $('#passField').val();
        var passwordConfirm = $('#passConfirmField').val();
        var country = $('#countryField').val();
        var gender = $('#genderField').val();
        var bDay = $('#bDayField').val();
        var dataFields = {
            "email": email,
            "username": username,
            "password": password,
            "country": country,
            "gender": gender,
            "bDay": bDay
        };
        if (checkPasswords(password, passwordConfirm)) {
            $.ajax({
                type: 'Post',
                url: 'registration',
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
        }
    });

    function checkPasswords(password, passwordConf) {
        if (password == passwordConf) {
            return true;
        } else {
            alert("Passwords do not match!");
            return false;
        }
    }
});