function leftToRightBtn() {
    //分配角色

    var selectedOptions = $("#leftRoleList option:selected");

    var jsonObj = {
        userid: $("#userid").val()
    };

    $.each(selectedOptions, function (i, n) {
        jsonObj["roleids[" + i + "]"] = this.value;
    });

    var index = -1;
    $.ajax({
        type: "POST",
        data: jsonObj,
        url: "/user/saveassignRlole.do",
        beforeSend: function () {
            index = layer.load(2, {time: 10 * 1000});
            return true;
        },
        success: function (result) {
            layer.close(index);
            if (result) {
                $("#rightRoleList").append(selectedOptions);
            } else {
                layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
            }
        },
        error: function () {
            layer.msg("操作失败!", {time: 1000, icon: 5, shift: 6});
        }

    });


}

function rightToLeftBtn() {


    var selectedOptions = $("#rightRoleList option:selected");

    var jsonObj = {
        userid: $("#userid").val()
    };

    $.each(selectedOptions, function (i, n) {
        jsonObj["roleids[" + i + "]"] = this.value;
    });

    var index = -1;
    $.ajax({
        type: "POST",
        data: jsonObj,
        url: "/user/deleteassignRlole.do",
        beforeSend: function () {
            index = layer.load(2, {time: 10 * 1000});
            return true;
        },
        success: function (result) {
            layer.close(index);
            if (result) {
                $("#leftRoleList").append(selectedOptions);
            } else {
                layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
            }
        },
        error: function () {
            layer.msg("操作失败!", {time: 1000, icon: 5, shift: 6});
        }

    });


}

//取消角色
