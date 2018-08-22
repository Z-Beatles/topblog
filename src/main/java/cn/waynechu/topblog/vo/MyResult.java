package cn.waynechu.topblog.vo;
/**
 * @Title: myResult
 * @ProjectName topblog
 * @Description: TODO
 * @author xiach
 * @date 2018/8/1610:04
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.Date;

/**
 *@author xiacunhai
 *@create 2018/8/16
 *结果返回信息
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class MyResult {

    /**
     * 正数成功状态编号
     * 非正数失败状态编号
     */
    private  Integer  statusCode;
    /**
     * 返回值，编号说明
     */
    private String msg;
    /**
     * 时间
     */
    private String stringTime = new Timestamp(System.currentTimeMillis()).toString();

    /**
     * 返回值
     */
    private Object result = new Object();



}
