package cn.waynechu.topblog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper=false)
@Data
public class AdminEntity implements Serializable{
    /** id **/
    private Long id;
    /** 用户名 **/
    private String username;
    /** 用户昵称 **/
    private String nickname;
    /** 联系电话 **/
    private String mobile;
    /** 邮箱 **/
    private String email;
    /** 角色名 **/
    private String roleName;
    /** 中文角色名 **/
    private String roleNameZh;

}
