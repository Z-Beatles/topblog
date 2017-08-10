package cn.waynechu.topblog.entity;

public class AdminEntity {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }

    @Override
    public String toString() {
        return "AdminEntity [id=" + id + ", username=" + username + ", nickname=" + nickname + ", mobile=" + mobile
                + ", email=" + email + ", roleName=" + roleName + ", roleNameZh=" + roleNameZh + "]";
    }

}
