package com.gh.controller;

import com.alibaba.fastjson.JSON;
import com.gh.Exception.LoingRunTime;
import com.gh.base.BaesColntroller;
import com.gh.pojo.TUser;
import com.gh.pojo.extendes.UserVo;
import com.gh.service.UserService;
import com.gh.utils.Condition;
import com.gh.utils.Const;
import com.gh.utils.MD5Util;
import com.gh.utils.PagingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/classpather")
public class DisPathController extends BaesColntroller {
    private static final Logger logger = LoggerFactory.getLogger(DisPathController.class);
    @Autowired
    @Qualifier("userService")
    private UserService userService;



    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String index() {
        logger.info("首页----start");
        return "index";
    }

    @RequestMapping(value = "/sgin.htm", method = RequestMethod.GET)
    public String sgin(@RequestParam(value = "method") String method) {
        if (method.trim().equals("login")) {
            logger.info("登录----start");
            return "login";
        } else if (method.trim().equals("reg")) {
            logger.info("注册----start");
            return "reg";
        } else if (method.trim().equals("main")) {
            logger.info("后台主页----start");
            return "main";

        }else if(method.trim().equals("role")){
            logger.info("角色----start");
            return "role";
        }
        return "";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Object loginStart(@Validated UserVo user, BindingResult bindingResult,
                             HttpSession session, HttpServletRequest request) throws SQLException {
        logger.info(user.getLoginacct());
        logger.info(user.getUserpswd());
        logger.info(user.toString());
        user.setUserpswd(MD5Util.digest(user.getUserpswd()));
        System.out.print(JSON.toJSON(user));
        if (!bindingResult(bindingResult)) {
            if (super.getCondition() != null) {
                logger.info(JSON.toJSONString(super.getCondition() ));
                return JSON.toJSONString(super.getCondition() );
            }
        }
        TUser tUser = userService.findLoignByUser(user);
        if (tUser == null) {
            logger.info("找不到用户");
        } else {
            session.setAttribute(Const.USER_SESSION, tUser);
        }
        /* return "redirect:/classpather/sgin.htm?method=main";*/
        return JSON.toJSONString(tUser);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.GET)
    public Object saveUser() throws SQLException {
        userService.saveUser(null);


        return 0;
    }

}
