package cn.waynechu.topblog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper=false)
@Data
public class LoginUserEntity implements Serializable{
    /** 用户id **/
    private Long id;
    /** 登录类型 **/
    private String loginType;
    /** 用户名 **/
    private String username;
    /** 头像 **/
    private String avatar;
    /** 密码Hash **/
    private String passwordHash;
    /** 密码Salt **/
    private String passwordSalt;
    /** 加密方式 **/
    private String passwordAlgo;
    /** 加密次数 **/
    private Integer passwordIteration;
    /** 禁用 **/
    private Boolean disabled;
    /** 锁定 **/
    private Boolean locked;

}
