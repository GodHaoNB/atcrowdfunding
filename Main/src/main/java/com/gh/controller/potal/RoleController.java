package com.gh.controller.potal;

import com.alibaba.fastjson.JSON;
import com.gh.pojo.extendes.RoleVo;
import com.gh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired
    @Qualifier("userService")
    RoleService roleService;

    @RequestMapping(value = "/edit.htm",method = RequestMethod.GET)
    public String doEdit(Integer id, HttpServletRequest request) throws SQLException {
        request.setAttribute("role",roleService.selectByPrimaryKey(id));
        return "editRole";
    }
    @RequestMapping(value ="/doEdit.do", method = RequestMethod.POST)
    @ResponseBody
    public String doEdit(RoleVo roleVo) throws SQLException {

        Integer integer = roleService.updateByPrimaryKeySelective(roleVo);
        boolean success = false;
        if(integer >0){
            success =true;
        }
        return JSON.toJSONString(success);
    }

}
