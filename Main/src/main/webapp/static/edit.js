var $Floginacct;
var $Fusername;
var $Femail;
var $EditFrom;
$(document).ready(function () {
    $Floginacct = $("#floginacct");
    $Fusername = $("#fusername");
    $Femail = $("#femail");
    $EditFrom = $("#editForm");
})
function editModfiy() {
    var loadingIndex = -1;
    $.ajax({
            type: 'post',
            dataType: "json",
            /* contentType: 'application/json;charset=UTF-8',*/
            data: $EditFrom.serializeArray(),
            url:  "/user/edit.do",
            //表单提交前执行
            beforeSend: function () {
                loadingIndex = mag('请求处理中', ICON_NUMSIX);
                return true;

            },
            success: function (data) {
                layer.close(loadingIndex);
                 console.log(data);
                if (data == true) {
                    window.location.href = "/user/sgin.htm?method=user";
                } else {
                    layer.msg("修改失败，请重新修改", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                }
            }
            ,
            error: function (data) {
                layer.msg("修改失败，请重新修改", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
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
function loginacctCheck() {
    var loginacctReg = /^[a-zA-Z\d]+$/;
    var check = datacheck($Floginacct.val(), loginacctReg);
    if (switctMagInfo(check)) {
        $Fusername.focus();
        return true;
    } else {
        $Floginacct.focus();
        return false;
    }
}
function usernameCheck() {
    var usernameReg = /^[a-zA-Z\d]+$/;
    var check = datacheck($Fusername.val(), usernameReg);
    if (switctMagInfo(check)) {
        $Femail.focus();
        return true;
    } else {
        $Fusername.focus();
        return false;
    }
}
function emailCheck() {
    var emailReg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$";
    var check = datacheck($Femail.val(), emailReg);
    if (switctMagInfo(check)) {
        return true;
    } else {
        $Femail.focus();
        return false;
    }
}