package cn.waynechu.topblog.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import com.google.common.base.Throwables;
import cn.waynechu.topblog.AppException;
import cn.waynechu.topblog.Constaint;

public class WebUtil {
    public static Map<String, Object> error(Exception exception, boolean trace) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", false);
        if (exception instanceof AppException) {
            AppException ae = (AppException) exception;
            result.put("code", ae.getErrorCode());
            result.put("message", ae.getErrorMessage());
            if (ae.getErrorCause() != null) {
                result.put("error", ae.getErrorCause().getClass().getName());
            }
        } else {
            result.put("code", Constaint.ERRORCODE_OTHER);
            result.put("message", exception.getMessage());
            result.put("error", exception.getClass().getName());
        }
        if (trace) {
            result.put("stacktrace", Throwables.getStackTraceAsString(exception));
        }
        return result;
    }

    public static HttpServletRequest getNativeRequest(WebRequest request) {
        HttpServletRequest req = ((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class);
        if (req instanceof ShiroHttpServletRequest) {
            return (HttpServletRequest) ((ShiroHttpServletRequest) req).getRequest();
        }
        return req;
    }

    public static String getShiroRequestURI(WebRequest request) {
        SavedRequest savedRequest = WebUtils.getSavedRequest(getNativeRequest(request));
        if (savedRequest != null) {
            return savedRequest.getRequestURI();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T withHttpSession(HttpSession session, String key, Supplier<T> supplier) {
        T result = (T) session.getAttribute(key);
        if (result == null) {
            synchronized (session) {
                result = supplier.get();
                session.setAttribute(key, result);
            }
        }
        return result;
    }

}
