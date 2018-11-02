$(document).ready(function () {
    $("#form").on('submit', function (e) {
        e.preventDefault();
        var email = $('#emailField').val();
        var password = $('#passField').val();
        var passwordConfirm = $('#passConfirmField').val();
        var country = $('#countryField').val();
        var gender = $('#genderField').val();
        var bDay = $('#bDayField').val();
        var dataFields = {
            "email": email,
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
                    if (result.emailError != null) {
                        alert(result.emailError);
                    }
                    if (result.passwordError != null) {
                        alert(result.passwordError);
                    }
                    if (result.bDayError != null) {
                        alert(result.bDayError);
                    }
                    if (result.existDBError != null) {
                        alert(result.existDBError);
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