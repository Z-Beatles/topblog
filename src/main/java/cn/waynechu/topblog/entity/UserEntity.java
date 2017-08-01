package cn.waynechu.topblog.entity;

public class UserEntity {
    /** 用户id **/
    private Long id;
    /** 用户名 **/
    private String username;
    /** 昵称 **/
    private String nickname;
    /** 联系电话 **/
    private String mobile;
    /** 邮箱 **/
    private String email;

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

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", username=" + username + ", nickname=" + nickname + ", mobile=" + mobile
                + ", email=" + email + "]";
    }

}
