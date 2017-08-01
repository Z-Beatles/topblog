package cn.waynechu.topblog.entity;

public class LoginUserEntity {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordAlgo() {
        return passwordAlgo;
    }

    public void setPasswordAlgo(String passwordAlgo) {
        this.passwordAlgo = passwordAlgo;
    }

    public Integer getPasswordIteration() {
        return passwordIteration;
    }

    public void setPasswordIteration(Integer passwordIteration) {
        this.passwordIteration = passwordIteration;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "LoginUserEntity [id=" + id + ", loginType=" + loginType + ", username=" + username + ", avatar="
                + avatar + ", passwordHash=" + passwordHash + ", passwordSalt=" + passwordSalt + ", passwordAlgo="
                + passwordAlgo + ", passwordIteration=" + passwordIteration + ", disabled=" + disabled + ", locked="
                + locked + "]";
    }

}
