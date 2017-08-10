package cn.waynechu.topblog.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

public class CustomJstlView extends JstlView {

    @Override
    protected void exposeHelpers(HttpServletRequest request) throws Exception {
        super.exposeHelpers(request);

        request.setAttribute("ctx", request.getServletContext().getContextPath());
        request.setAttribute("ch", getApplicationContext().getBean(ContextHelper.class));
        request.setAttribute("req", request);
    }

}
