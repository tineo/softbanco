package com.softbanco.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class User  implements java.io.Serializable {
    String userId;
    String password;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
