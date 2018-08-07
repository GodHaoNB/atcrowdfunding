var $fname;
var $furl;
var $ficon;
var $addForm;

jQuery(document).ready(function ($) {
    $addForm = $("#addForm");
    $fname = $("#fname");
    $furl = $("#furl");
    $ficon = $("#ficon");
});
var loginacctBool = false;
var userPwdBool = false;
var iconBool=false;
function nameCheck() {
    var loginacctReg = /^[a-zA-Z\d]+$/;
    var check = datacheck($fname.val(), loginacctReg);
    if (switctMagInfo(check)) {
        $furl.focus();
        loginacctBool =true;
        return true;
    } else {
        $fname.focus();
        return false;
    }
}

function urlCheck() {
    var userpswdTest = /^[a-zA-Z\d]+$/;
    var check = datacheck($furl.val(), userpswdTest);
    if (switctMagInfo(check)) {
        userPwdBool = true;
        return true;
    } else {
        $furl.focus();
        return false;
    }
}
function iconCheck() {
    if($ficon.val()==0){
        mag("请选择icon",ICON_NUFIVE);
    }else {
        iconBool =true;
    }
}

function btnadd() {
    console.log($addForm.serializeArray());

    var loadingIndex = -1;
    $.ajax({
            type: 'post',
            dataType: "json",
            /* contentType: 'application/json;charset=UTF-8',*/
            data: $addForm.serializeArray(),
            url: BASE_URL + "/permission/toAdd.do",
            //表单提交前执行
            beforeSend: function () {
                if(loginacctBool&&userPwdBool){
                    loadingIndex = mag('处理中', ICON_NUMSIX);
                    return true;
                }else{
                    loadingIndex = mag('所有不可为空', ICON_NUFIVE);
                    return false;
                }


            },
            success: function (data) {
                layer.close(loadingIndex);
                /* console.log(data.serializeArray());*/
                if (data != null) {
                    window.location.href = BASE_URL+"/permission/sgin.htm?method=permission";
                } else {
                    loadingIndex = mag('添加失败', ICON_NUFIVE);
                }
            }
            ,
            error: function (data) {
                layer.msg("添加失败", {time: 1000, icon: 5, shift: 6}, $fname.focus());
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



