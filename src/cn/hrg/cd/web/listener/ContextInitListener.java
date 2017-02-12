package cn.hrg.cd.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.hrg.cd.util.Global;

@WebListener
public class ContextInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextInitListener...");
		Global.webAppPath = sce.getServletContext().getRealPath("/");
		System.out.println(Global.webAppPath);
	}

}
