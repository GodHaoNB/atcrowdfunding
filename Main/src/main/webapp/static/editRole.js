$(function () {
    $("#updateBtn").click(function () {
        var loadingIndex = -1;
        console.log($("#roleid").val())
        $.ajax({

            url: BASE_URL +"/role/doEdit.do",
            type: "POST",
            data: {
                id: $("#roleid").val(),
                name: $("#name").val()


            },
            beforeSend: function () {
                loadingIndex = layer.msg('数据正在修改中', {icon: 6});
                return true;
            },
            success: function (result) {
                layer.close(loadingIndex);
                if (result) {
                    layer.msg("角色数据修改成功", {time: 1000, icon: 6});
                    window.location.href = "/user/sgin.htm?method=role";
                } else {
                    layer.msg("角色数据修改失败", {time: 1000, icon: 5, shift: 6});
                }

            },
            error: function () {
                layer.msg("角色数据修改失败", {time: 2000, icon: 5, shift: 6});
            }

        });

    });


    $("#resetBtn").click(function () {
        $("#updateForm")[0].reset();
    });

});