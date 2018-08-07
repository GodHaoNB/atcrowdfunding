package com.gh.controller.potal;

import com.alibaba.fastjson.JSON;
import com.gh.pojo.extendes.PermissionVo;
import com.gh.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    @Qualifier("permissionService")
    PermissionService permissionService;
    @RequestMapping(value = "/sgin.htm", method = RequestMethod.GET)
    public String sgin(@RequestParam(value = "method") String method
    ) {
        if (method.equals("permission")) {
            return "permission";
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/loadData.do", method = RequestMethod.POST)
    public Object permission() throws SQLException {
       /* logger.info(JSON.toJSONString(permissionService.findAllPrimary()));*/
        return permissionService.findAllPrimaryZTree();
    }
    @RequestMapping(value = "/toAdd.htm", method = RequestMethod.GET)
    public Object toAdd(Integer id , HttpServletRequest request) throws SQLException {
        request.setAttribute("pid",id);
        List<PermissionVo> permissionVos =permissionService.findAllPrimarys() ;
        request.setAttribute("permissionVos",permissionVos);
        return "permissionAdd";
    }
    @RequestMapping(value = "/toAdd.do", method = RequestMethod.POST)
    @ResponseBody
    public Object toAdd(PermissionVo permissionVo) throws SQLException {
        return JSON.toJSONString(permissionService.savePermission(permissionVo));
    }
}
