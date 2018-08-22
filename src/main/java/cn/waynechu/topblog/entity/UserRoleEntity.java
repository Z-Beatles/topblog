package cn.waynechu.topblog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Title: UserRoleEntity
 * @ProjectName topblog
 * @Description:用户角色
 * @author xiach
 * @date 2018/8/1610:19
 */
@EqualsAndHashCode
@Data
public class UserRoleEntity {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer loginUserId;
    /**
     * 角色ID
     */
    private Integer roleId;

}
