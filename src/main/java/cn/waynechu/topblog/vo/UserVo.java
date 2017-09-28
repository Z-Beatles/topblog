package cn.waynechu.topblog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiach on 2017/9/28.
 * 用户模块的展示对象
 *
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class UserVo implements Serializable{
    private long id; //登录id
    private String avatar; //头像
    private String username; //用户名
    private String nickname;// 昵称
    private String mobile; // 联系电话
    private String email;// 邮箱
    private Boolean disabled;//禁用
    private Boolean locked;//锁定
}
