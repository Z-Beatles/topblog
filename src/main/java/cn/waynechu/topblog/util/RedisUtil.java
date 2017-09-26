package cn.waynechu.topblog.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * 自定义的工具，xiach
 */
public class RedisUtil {
    public static Jedis getJedis(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-basic.xml");
        Jedis jedis = (Jedis) context.getBean("jedis");
        return jedis;
    }
}
