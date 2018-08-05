package com.gh.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class StartSystemListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartSystemListener.class);

    /**
     * 在服务其启动时，加载appliction对象是需要执行的方法
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("StartSystemListener.contextInitialized(ServletContextEvent " +
                "servletContextEvent)--->START");
        ServletContext AppliCtionContext = servletContextEvent.getServletContext();
        //将上下文路径放入AppliCtion 作用域中
        AppliCtionContext.setAttribute("APPLI_PATH", AppliCtionContext.getContextPath());
        logger.info("StartSystemListener.contextInitialized(ServletContextEvent " +
                "servletContextEvent)--->END");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("StartSystemListener.contextDestroyed(ServletContextEvent " +
                "servletContextEvent)--->START");
        ServletContext AppliCtionContext = servletContextEvent.getServletContext();
        //将上下文路径移除AppliCtion 作用域中
        AppliCtionContext.removeAttribute("APPLI_PATH");
        logger.info("StartSystemListener.contextDestroyed(ServletContextEvent " +
                "servletContextEvent)--->END");
    }
}
