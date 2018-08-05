package com.gh.Exception;

import com.gh.utils.Const;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义异常
 */
public class LoingRunTime extends RuntimeException {
    public LoingRunTime(String message){
        super(message);
    }


}
