
var jsonObj;
$(document).ready(function () {

    jsonObj = {
        "page": 1,
        "rows": 10,
        "where": ""
    };
    queryPageUser();
})

var loadingIndex = -1;

function queryPageUser() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/user/user.do",
        data: {
            "page": jsonObj.page,
            "rows": jsonObj.rows,
            "where": jsonObj.where
        },

        beforeSend: function () {
            loadingIndex = layer.load(2, {time: 10 * 1000});
            return true;
        },
        success: function (result) {
            layer.close(loadingIndex);
            var dataPage = result;
            /* console.log(dataPage.data_success);
             console.log(dataPage.query_data.rows);*/
            if (result.data_success) {
                var page = result.query_data;
                var data = page.rows;
                var indexpage = page.total / page.pageSize;
                /*console.log(page.page)*/
                var content = '';

                $.each(data, function (i, n) {
                    content += '<tr>';
                    content += '  <td>' + (i + 1) + '</td>';
                    content += '  <td><input id="' + n.id + '" type="checkbox"></td>';
                    content += '  <td>' + n.loginacct + '</td>';
                    content += '  <td>' + n.username + '</td>';
                    content += '  <td>' + n.email + '</td>';
                    content += '  <td>';
                    content += '	  <button id="btn-success" onclick="assignRole('+n.id+');"  type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                    content += '	  <button onclick="edit(' + n.id + ')" id="btn-primary" value="' + n.id + '" type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                    content += '	  <button onclick="deleteuser(' + n.id + ',\'' + n.username + '\')" id="btn-danger" value="' + n.username + '" type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                    content += '  </td>';
                    content += '</tr>';
                });


                $("#tbdoy").html(content);

                var contentBar = '';

                if (page.page == 1) {
                    contentBar += '<li class="disabled"><a href="#">上一页</a></li>';
                } else {
                    contentBar += '<li><a href="#" onclick="pageChange(' + (page.page - 1) + ')">上一页</a></li>';
                }

                for (var i = 0; i <= indexpage; i++) {
                    contentBar += '<li  ';
                    console.log(page.page)
                    console.log(jsonObj.page)
                    console.log(page.page == jsonObj.page);
                    if (page.page == jsonObj.page) {
                        contentBar += 'class="active"';
                        /* contentBar += 'class="active" ';*/
                    }
                    contentBar += '><a href="javascript:void(0)" onclick="pageChange(' + (i + 1) + ')">' + (i + 1) + '</a></li>';
                }

                if (page.page >= indexpage || indexpage < 1) {
                    contentBar += '<li class="disabled"><a href="#">下一页</a></li>';
                } else {
                    contentBar += '<li><a href="javascript:void(0)" onclick="pageChange(' + (page.page + 1) + ')">下一页</a></li>';
                }

                $(".pagination").html(contentBar);

            } else {
                layer.msg("数据为空", {time: 1000, icon: 5, shift: 6});
            }
        },
        error: function (result) {
            layer.msg("数据请求失败", {time: 1000, icon: 5, shift: 6});
        }
    });
}

function pageChange(indexpage) {
    jsonObj.page = indexpage;
    queryPageUser(jsonObj);

}

function likeWhereSearch() {
    var $li = $(".active");
    var a_page = $li.find("a").innerHTML;
    jsonObj.where = $("#where").val();
    jsonObj.page = a_page;
    queryPageUser();

}

function edit(id) {
    window.location.href = "/user/edit.htm?id=" + id;
}

function deleteuser(id, name) {
    layer.confirm("确认要删除用户" + name, {icon: 3, title: '提示'},
        function (cindex) {
            layer.close(cindex);
            del();
        },
        function (cindex) {
            layer.close(cindex);
        });

    function del() {

        var loadingIndex = -1;
        $.ajax({
                type: 'post',
                dataType: "json",
                /* contentType: 'application/json;charset=UTF-8',*/
                data: {"id": id},
                url: "/user/delete.do",
                beforeSend: function () {
                    loadingIndex = mag('处理中,', ICON_NUMSIX);
                    return true;

                },
                success: function (data) {
                    layer.close(loadingIndex);
                    /* console.log(data.serializeArray());*/
                    layer.close(loadingIndex);
                    console.log(data);
                    if (data == true) {
                        window.location.href = "/user/sgin.htm?method=user";
                    } else {
                        layer.msg("删除失败，请联系管理员", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    }
                }
                ,
                error: function (data) {
                    layer.msg("删除失败，请联系管理员", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
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

}

$("#Allcheckbox").click(function () {
    //获得当前复选框的状态 true /false
    var checkboxStatus = this.checked;
    //设置 当前 数据的所有复选框状态都为true 即选中状态
    var allcheckbox = $("tbody tr td input[type=checkbox]").prop("checked", checkboxStatus);
    console.log(allcheckbox);

    /* for (var i = 0;i<allcheckbox.length;i++){
         console.log(allcheckbox[i]);
         allcheckbox[i].attr("checked",checkboxStatus);
     }*/
    /*  $.each(allcheckbox,function (index,checkbox) {

         /!* if(!checkbox.attr("checked")==checkboxStatus){
              checkbox.attr("checked",checkboxStatus);
          }*!/
      })*/
});

/*批量删除*/
function deleteUserBach() {
    var SelectCheckbox = $("tbody tr td input:checked");
    var dataString = "";
    if (SelectCheckbox.length == 0) {
        layer.confirm("删除用户不能为空?", {icon: 3, title: '提示'})
        return;
    }

    $.each(SelectCheckbox, function (i, n) {
        if (i != 0) {
            dataString += "&";
        }
        dataString += "id=" + n.id;
    })
    layer.confirm("确认要删除选中用户吗?", {icon: 3, title: '提示'},
        function (cindex) {
            layer.close(cindex);
            del();
        },
        function (cindex) {
            layer.close(cindex);
        });

    function del() {

        var loadingIndex = -1;
        $.ajax({
                type: 'post',
                dataType: "json",
                /* contentType: 'application/json;charset=UTF-8',*/
                data: dataString,
                url: "/user/deletebsch.do",
                beforeSend: function () {
                    loadingIndex = mag('处理中,', ICON_NUMSIX);
                    return true;

                },
                success: function (data) {
                    layer.close(loadingIndex);
                    /* console.log(data.serializeArray());*/
                    console.log(data);
                    if (data == 1) {
                        window.location.href = "/user/sgin.htm?method=user";
                    } else if (data == -1) {
                        layer.msg("删除失败,未选中用户，请联系管理员", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    } else if (data == 0) {
                        layer.msg("删除失败，请联系管理员", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    } else {
                        layer.msg("未知错误！！", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    }
                }
                ,
                error: function (data) {
                    if (data == -1) {
                        layer.msg("删除失败,未选中用户，请联系管理员", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    } else if (data == 0) {
                        layer.msg("删除失败，请联系管理员", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    } else {
                        layer.msg("未知错误！！", {time: 1000, icon: 5, shift: 6}, $Floginacct.focus());
                    }
                }
                ,
                complete: function (data) {
                    /* console.log(JSON.stringify(data.serializeArray()));*/
                },
                clearForm: false,//禁止清楚表单
                resetForm: false //禁止重置表单

            }
        )

        console.log(dataString);
    }
}

$("#tbdoy .btn-success").click(function () {

});

/*

$("#btn-primary").click(function () {

});*/
function assignRole(id) {
    window.location.href = "/user/assignRole.htm?id="+id;
}