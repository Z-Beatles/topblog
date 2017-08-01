package cn.waynechu.topblog;

import java.util.HashMap;
import java.util.Map;

public class Result<T> {
	private T target;
	private AppException error;
	private Map<String, Object> attrs = new HashMap<>();

	public static <T> Result<T> target(T t) {
		Result<T> result = new Result<>();
		result.target = t;
		return result;
	}

	public static <T> Result<T> error(String errorMessage) {
		Result<T> result = new Result<>();
		result.error = new AppException(Constaint.ERRORCODE_OTHER, errorMessage);
		return result;
	}

	public T result() {
		return target;
	}

	public boolean hasError() {
		return error != null;
	}

	public AppException error() {
		return error;
	}

	public T target() {
		return target;
	}

	public void attr(String key, Object attr) {
		attrs.put(key, attr);
	}

	public Object attr(String key) {
		return attrs.get(key);
	}
}
