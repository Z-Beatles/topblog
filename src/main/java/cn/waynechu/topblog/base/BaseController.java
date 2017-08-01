package cn.waynechu.topblog.base;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.waynechu.topblog.util.WebUtil;

@Component
public abstract class BaseController{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
     
	@ExceptionHandler
	protected String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception, Model model) {
		boolean trace = request.getParameterMap().containsKey("debug") && !"false".equalsIgnoreCase(request.getParameter("debug"));
		if (trace) {
			logger.warn("请求错误,请求:{}", request.getRequestURI(), exception);
		} else {
			logger.warn("请求错误,请求:{},错误:{}", request.getRequestURI(), exception.getMessage());
		}
		Map<String, Object> error = WebUtil.error(exception, trace);
		if (request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.writeValue(response.getWriter(), error);

			} catch (IOException e) {
			}
			return null;
		} else {
			model.addAttribute("error", error);
			String modelname = model.getClass().getName();
			return StringUtils.isNotBlank(modelname) ? ("/" + modelname + "/error") : "/error";
		}
	}

}
