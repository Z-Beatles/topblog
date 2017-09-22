package cn.waynechu.topblog.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MapUtil {
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> asMap(Object... arr) {
		Map<String, T> result = new HashMap<>();
		if (ArrayUtils.isNotEmpty(arr)) {
			if (arr.length % 2 != 0) {
				throw new RuntimeException("数组个数为奇数");
			}
			for (int i = 0; i < arr.length / 2; i++) {
				Object key = arr[2 * i];
				T value = (T) arr[2 * i + 1];
				if (!(key instanceof String)) {
					throw new RuntimeException("数据[" + key + "]不为字符类型");
				}
				result.put((String) key, value);
			}
		}
		return result;
	}

	public static <T, R> R getOrDefault(Map<T, R> map, T key, Function<T, R> func) {
		R result = map.get(key);
		if (result == null) {
			result = func.apply(key);
			map.put(key, result);
		}
		return result;
	}
}
