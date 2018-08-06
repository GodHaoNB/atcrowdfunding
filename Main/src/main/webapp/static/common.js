/**
 * 数据校验
 * @param str 需要校验的字段
 * @param test 正则表达式
 */
var ICON_NUMONE = 1;
var ICON_NUMTWO = 2;
var ICON_NUMTHREE = 3;
var ICON_NUMFOUR = 4;
var ICON_NUFIVE = 5;
var ICON_NUMSIX = 6;

function datacheck(str, test) {
    var reg = test;
    if ($.trim(str).length >0) {
        if ($.trim(reg).length >0) {
            if (test.test(str)) {
                return 1;
            } else {
                return -1;
            }

        } else {
            return 1;
        }

    } else {
        return 0;
    }
}

function mag(maginfo, icon) {
    return layer.msg(maginfo, {time: 1000, icon: icon, shift: 6});
}

function switctMagInfo(check) {
    var checkBool = true;
    switch (check) {
        case 0:
            checkBool = false;
            mag("验证失败，不可为空", ICON_NUFIVE);
            break;
        case -1:
            checkBool = false;
            mag("验证失败，不符合规范", ICON_NUFIVE);
            break;
        case 1:
            checkBool = true;
            mag("验证成功，很棒", ICON_NUMSIX);
            break;
    }
    return checkBool;
}


function getRoot() {
    var hostname = location.hostname;
    var port = location.port;
    var protocol = location.protocol;
    return protocol + "//" + hostname + ":" + port;

}
var BASE_URL = getRoot();