package com.gh.interceptor;

import com.gh.pojo.TUser;
import com.gh.utils.Const;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HendlerInterceptor implements HandlerInterceptor {
    /**
     * 进入Hendler方法之前， 用于身份认证，权限分配
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;

    }

    /**
     * 进入Hendler方法之后，返回ModelAndView之后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       /* String url = request.getRequestURI();
        System.out.print(url);
        if(url.indexOf("index")>=0){
        }else {
            HttpSession session = request.getSession();
            TUser user = (TUser) session.getAttribute(Const.USER_SESSION);
            if (user == null) {
                modelAndView.setViewName("login");
            }
        }*/
    }

    /**
     * 进入Hendler完成，执行此方法
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
