$(document).ready(function () {
    $("#forms").on('submit', function (e) {
        e.preventDefault();
        var email = $('#emailField').val();
        var password = $('#passField').val();
        var passwordConfirm = $('#passConfirmField').val();
        var country = $('#countryField').val();
        var sex = $('#sexField').val();
        var bDay = $('#bDay').val();
        var dataFields = {
            "email": email,
            "password": password,
            "country": country,
            "sex": sex,
            "bDay": bDay
        };
        if (checkPasswords(password, passwordConfirm)) {
            $.ajax({
                type: 'Post',
                url: 'registration',
                data: dataFields,
                success: function (result) {
                    window.location.href = result.getParameter("uri");
                    alert("SUCCESSSSSSSSSSS SSSSSSSSSSSSSs");
                },
                error: function (result) {
                    alert("FAILLLLLLLLLLLL");
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