var $Floginacct;//登录名对象
var $Fuserpswd;//登录密码对象
var $Ftype;//登录类型对象
var $loginForm;//登录表单对象

jQuery(document).ready(function ($) {
    $loginForm = $("#loginForm");
    $Floginacct = $("#floginacct");
    $Fuserpswd = $("#fuserpswd");
    $Ftype = $("#ftype");
});
var loginacctBool = false;
var userPwdBool = false;
function loginacctCheck() {
    var loginacctReg = /^[a-zA-Z\d]+$/;
    var check = datacheck($Floginacct.val(), loginacctReg);
    if (switctMagInfo(check)) {
        $Fuserpswd.focus();
        loginacctBool =true;
        return true;
    } else {
        $Floginacct.focus();
        return false;
    }
}

function userpswdCheck() {
    var userpswdTest = /^[a-zA-Z\d]+$/;
    var check = datacheck($Fuserpswd.val(), userpswdTest);
    if (switctMagInfo(check)) {
        userPwdBool = true;
        return true;
    } else {
        $Fuserpswd.focus();
        return false;
    }
}


function btnLogin() {
    console.log($loginForm.serializeArray());

    var loadingIndex = -1;
    $.ajax({
            type: 'post',
            dataType: "json",
            /* contentType: 'application/json;charset=UTF-8',*/
            data: $loginForm.serializeArray(),
            url: BASE_URL + "/login.do",
            //表单提交前执行
            beforeSend: function () {
                if(loginacctBool&&userPwdBool){
                    loadingIndex = mag('处理中', ICON_NUMSIX);
                    return true;
                }else{
                    loadingIndex = mag('登录名与密码均不可为空', ICON_NUFIVE);
                    return false;
                }


            },
            success: function (data) {
                layer.close(loadingIndex);
                /* console.log(data.serializeArray());*/
                if (data != null) {
                    window.location.href = BASE_URL+"/sgin.htm?method=main";
                } else {
                    window.location.href = BASE_URL+"/sgin.htm?method=login";
                }
            }
            ,
            error: function (data) {
                layer.msg("登陆失败", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                /* console.log(JSON.stringify(data.serializeArray()));*/
            }
            ,
            complete: function (data) {
                /* console.log(JSON.stringify(data.serializeArray()));*/
            },
            clearForm: false,//禁止清楚表单
            resetForm: false //禁止重置表单

        }
    )


}



