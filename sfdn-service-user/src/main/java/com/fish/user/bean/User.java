package com.fish.user.bean;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Transient;


@ApiModel(description = "资源数据对象")
public class User implements Serializable {
    @ApiModelProperty(value = "扩展的id", name = "id",required = true)
    @Column(name = "id")//指定不符合第3条规则的字段名
    private Integer id;

    @ApiModelProperty(value = "用户名称", name = "name",required = true)
    private String name;

    @ApiModelProperty(value = "登录密码", name = "password",required = true)
    private String password;

    @ApiModelProperty(value = "手机号码", name = "phone",required = true)
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}