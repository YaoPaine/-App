package com.yao.lib_common.model.entity;

import java.io.Serializable;

/**
 * Created by heyao on 2017/10/25.
 */

public class UserModel implements Serializable {
    private String name;
    private String password;

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
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
}
