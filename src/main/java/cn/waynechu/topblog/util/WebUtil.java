package cn.waynechu.topblog.util;

import cn.waynechu.topblog.AppException;
import cn.waynechu.topblog.Constaint;
import cn.waynechu.topblog.Result;
import com.google.common.base.Throwables;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebUtil {
    public static <T> Map<String, Object> result(String successMsg, String errorMsg, Result<T> data) {
        Map<String, Object> result = new LinkedHashMap<>();
        if (data.hasError()) {
            result.put("success", false);
            result.put("message", errorMsg);
            result.put("error", data.error());
        } else {
            result.put("success", true);
            result.put("message", successMsg);
            result.put("data", data.target());
        }
        return result;
    }

    public static Map<String, Object> success(String message) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);
        result.put("message", message);
        return result;
    }

    public static Map<String, Object> success(String message, Object obj, String... candidates) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);
        result.put("message", message);
        if (obj == null) {
            result.put("data", null);
        } else if (ObjectUtil.isListSupport(obj)) {
            result.put("data", ObjectUtil.toDataList(obj, candidates));
        } else {
            result.put("data", ObjectUtil.toDataObject(obj, candidates));
        }
        return result;
    }

    public static Map<String, Object> error(String message) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", false);
        result.put("message", message);
        return result;
    }

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

    public static Map<String, ?> dataTable(List<?> data, Integer draw, Object recordsTotal, Object recordsFiltered) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);
        result.put("data", data);
        result.put("draw", draw);
        result.put("recordsTotal", recordsTotal);
        result.put("recordsFiltered", recordsFiltered);
        return result;
    }

}
