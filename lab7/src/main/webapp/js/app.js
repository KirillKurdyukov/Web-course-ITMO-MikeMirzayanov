window.notify = function (message, className) {
    $.notify(message, {
        position: "right bottom",
        className
    });
}

ajax = function (data, success) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data,
        success
    })
}

ajaxForm = function (action, $form) {
    const login = $form.find("input[name='login']").val();
    const password = $form.find("input[name='password']").val();
    const $error = $form.find(".error");
    const data = {
        action,
        login,
        password
    }
    const  success = function (response) {
        if (response["error"]) {
            $error.text(response["error"]);
        } else {
            location.href = response["redirect"];
        }
    }
    ajax(data, success)
}