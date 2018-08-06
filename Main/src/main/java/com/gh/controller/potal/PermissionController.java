package com.gh.controller.potal;

import com.alibaba.fastjson.JSON;
import com.gh.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    @Qualifier("permissionService")
    PermissionService permissionService;
    @RequestMapping(value = "/getpermission.htm", method = RequestMethod.GET)
    public Object permission() throws SQLException {
        logger.info(JSON.toJSONString(permissionService.findAllPrimary()));
        return "reg";
    }
}
