package cn.waynechu.topblog.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class LoginAuthenticationToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
    private static final long serialVersionUID = -169398369659102216L;

    private String loginType;
    private String username;
    private String password;
    private boolean rememberMe = false;
    private String host;

    public LoginAuthenticationToken(String loginType, String username, String password, boolean rememberMe, String host) {
        this.loginType = loginType;
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getLoginType() {
        return loginType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Object getPrincipal() {
        return "[" + loginType + "," + username + "]";
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String getHost() {
        return host;
    }

}
