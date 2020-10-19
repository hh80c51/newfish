package com.fish.framework.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * druidProperties类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 11:35
 **/
@Component
@Configuration
@ConfigurationProperties(prefix = "hope.druid")
@Order(-1)
public class DruidProperties {
    private String username;
    private String password;
    private String servletPath = "/druid/*";
    private Boolean resetEnable = false;
    private List<String> allowIps;
    private List<String> denyIps;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public Boolean getResetEnable() {
        return resetEnable;
    }

    public void setResetEnable(Boolean resetEnable) {
        this.resetEnable = resetEnable;
    }

    public List<String> getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(List<String> allowIps) {
        this.allowIps = allowIps;
    }

    public List<String> getDenyIps() {
        return denyIps;
    }

    public void setDenyIps(List<String> denyIps) {
        this.denyIps = denyIps;
    }
}