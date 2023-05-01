package com.flyinghead.vf4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class BaseVf4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected AutowireCapableBeanFactory ctx;

	@Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ctx = context.getAutowireCapableBeanFactory();
        ctx.autowireBean(this);
    }
}
