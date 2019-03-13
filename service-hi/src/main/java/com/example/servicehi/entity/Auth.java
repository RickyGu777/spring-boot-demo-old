package com.example.servicehi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Objects;

@Alias("Auth")
@ToString
@Getter
@Setter
public class Auth extends BaseEntity {
    private String method;
    private String className;
    private String type;
    private String url;
    private String authName;
    private String defaultCheck;
    private String authType;
    private String classGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(method, auth.method) &&
                Objects.equals(className, auth.className) &&
                Objects.equals(type, auth.type) &&
                Objects.equals(url, auth.url) &&
                Objects.equals(authName, auth.authName) &&
                Objects.equals(defaultCheck, auth.defaultCheck) &&
                Objects.equals(authType, auth.authType) &&
                Objects.equals(classGroup, auth.classGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, className, type, url, authName, defaultCheck, authType, classGroup);
    }
}