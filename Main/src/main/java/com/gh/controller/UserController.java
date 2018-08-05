package com.gh.controller;

import com.alibaba.fastjson.JSON;
import com.gh.base.BaesColntroller;
import com.gh.pojo.extendes.RoleVo;
import com.gh.pojo.extendes.UserVo;
import com.gh.service.RoleService;
import com.gh.service.UserService;
import com.gh.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaesColntroller {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    @Qualifier("userService")
    UserService userService;
    @Autowired
    @Qualifier("userService")
    RoleService roleService;

    @RequestMapping(value = "/sgin.htm", method = RequestMethod.GET)
    public String sgin(@RequestParam(value = "method") String method
    ) {
        if (method.equals("user")) {
            return "user";
        } else if (method.equals("role")) {
            return "role";
        }
        return "";
    }


    @RequestMapping(value = "/user.do", method = RequestMethod.POST)
    @ResponseBody
    public String AdminUser(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "rows", defaultValue = "10") int rows,
                            @RequestParam(value = "where", required = false, defaultValue = "null") String where,
                            HttpServletRequest request, HttpSession session) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        if (where != null && !where.trim().equals("null")) {
            if (!CharUtil.isChinese(where) && !CharUtil.isEmail(where)) {
                params.put("loginacct", where);
            }
            //判断是否包含汉字 是否
            if (CharUtil.isChinese(where)) {
                params.put("username", where);
            }
            //验证邮箱 是否
            if (CharUtil.isEmail(where)) {
                params.put("email", where);
            }

        }
        logger.info("查询条件" + JSON.toJSONString(params));
        AjaxResult ajaxResult = new AjaxResult();
        PagingResult<UserVo> userVoPagingResult = null;
        try {
            userVoPagingResult = userService.findForPageUser(page, rows, params);
            if (userVoPagingResult != null) {
                ajaxResult.put(Const.DATA_SUCCESS, true);
                ajaxResult.put(Const.DATA_QUWRY, userVoPagingResult);
            } else {
                ajaxResult.put(Const.DATA_SUCCESS, false);
                ajaxResult.put(Const.DATA_QUWRY, "请求数据为空");
            }
        } catch (SQLException e) {
            ajaxResult.put(Const.DATA_ERROR, false);
            ajaxResult.put(Const.DATA_QUWRY, "请求服务器失败，正在处理");
            e.printStackTrace();
        }
        logger.info(JSON.toJSONString(ajaxResult.getParams()));
        logger.info("总记录数" + userVoPagingResult.getTotal());
        logger.info("每页数量" + userVoPagingResult.getPageSize());
        userVoPagingResult.getRows().forEach(row -> logger.info(JSON.toJSONString(row)));
        request.setAttribute("userVoPagingResult", userVoPagingResult);
        return JSON.toJSONString(ajaxResult.getParams());
    }

    @RequestMapping("/role.do")
    @ResponseBody
    public Object AdminRole(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "rows", defaultValue = "10") int rows,
                            @RequestParam(value = "where", required = false, defaultValue ="") String where) throws SQLException {

        AjaxResult ajaxResult = new AjaxResult();
        PagingResult<RoleVo> roleVoPagingResult = null;
        try {
            RoleVo roleVo = new RoleVo();
            if(where != null && !where.trim().equals(" ")){
                roleVo.setName(where);
            }

            roleVoPagingResult = roleService.findForPageRole(page, rows,roleVo );
            if (roleVoPagingResult != null) {
                ajaxResult.put(Const.DATA_SUCCESS, true);
                ajaxResult.put(Const.DATA_QUWRY, roleVoPagingResult);
            } else {
                ajaxResult.put(Const.DATA_SUCCESS, false);
                ajaxResult.put(Const.DATA_QUWRY, "请求数据为空");
            }
        } catch (SQLException e) {
            ajaxResult.put(Const.DATA_ERROR, false);
            ajaxResult.put(Const.DATA_QUWRY, "请求服务器失败，正在处理");
            e.printStackTrace();
        }
        logger.info(JSON.toJSONString(ajaxResult.getParams()));
        logger.info("总记录数" + roleVoPagingResult.getTotal());
        logger.info("每页数量" + roleVoPagingResult.getPageSize());
        roleVoPagingResult.getRows().forEach(row -> logger.info(JSON.toJSONString(row)));
        return JSON.toJSONString(ajaxResult.getParams());
    }

    /**
     * 用户修改，查找用户
     *
     * @param id
     * @param request
     * @return
     * @throws SQLException
     */
    @RequestMapping("/edit.htm")
    public String userEdit(@RequestParam(value = "id") Integer id, HttpServletRequest request) throws SQLException {
        request.setAttribute("userVo", userService.findEidt(id));
        return "edit";
    }

    @RequestMapping("/edit.do")
    @ResponseBody
    public Object userEdit(UserVo user) throws SQLException {
        logger.info(user.getLoginacct());
        logger.info(user.toString());
        logger.info(JSON.toJSONString(user));
        Integer integer = userService.editUser(user);
        boolean bool = false;
        if (integer > 0) {
            bool = true;
        }
        logger.info(JSON.toJSONString(integer));
        return JSON.toJSONString(bool);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Object deleteUser(@RequestParam("id") Integer id) throws SQLException {
        Integer integer = userService.deleteUserById(id);
        boolean bool = false;
        if (integer > 0) {
            bool = true;
        }
        return JSON.toJSONString(bool);
    }

    @RequestMapping("/deletebsch.do")
    @ResponseBody
    public Object deletebschUser(Integer[] id) throws SQLException {

        Integer integer = userService.deleteUserById(id);
        return JSON.toJSONString(integer);
    }
    @ResponseBody
    @RequestMapping("/deleterole.do")
    public  Object deleteRole(Integer id) throws SQLException {
        Integer integer = roleService.deleteByPrimaryKey(id);
        boolean bool = false;
        if (integer > 0) {
            bool = true;
        }
        return JSON.toJSONString(bool);
    }
    @RequestMapping("/deleteRolebsch.do")
    @ResponseBody
    public Object deleteRolebsch(Integer[] id) throws SQLException {

        Integer integer = roleService.deleteRoleByIds(id);
        return JSON.toJSONString(integer);
    }
}
