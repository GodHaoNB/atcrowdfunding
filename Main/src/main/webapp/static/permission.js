

$(document).ready(function () {
    var setting = {
        view : {
            addDiyDom: function(treeId, treeNode){
                var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                if ( treeNode.icon ) {
                    icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
                }
            },
            addHoverDom: function(treeId, treeNode){   //设置自定义按钮组,在节点后面悬停显示增删改按钮组.
                var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                aObj.attr("href", "javascript:;"); // 取消当前链接事件.
                if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
                var s = '<span id="btnGroup'+treeNode.tId+'">';
                if ( treeNode.level == 0 ) { //根节点
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'/permission/toAdd.htm?id='+treeNode.id+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                } else if ( treeNode.level == 1 ) { //分支节点
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                    if (treeNode.children.length == 0) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'/permission/toAdd.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                } else if ( treeNode.level == 2 ) { //叶子节点
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"  onclick="window.location.href=\'/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                }

                s += '</span>';
                aObj.after(s);
            },
            removeHoverDom: function(treeId, treeNode){
                $("#btnGroup"+treeNode.tId).remove();
            }

        }
    };





    $.ajax({
        type:"post",
        dataType:"json",
        url:BASE_URL+"/permission/loadData.do",
        success:function(result){
            if(result!=null){
                $.fn.zTree.init($("#treeDemo"), setting, result);
            }else{
                alert("加载数据失败...");
            }
        }
    });

})




