package com.github.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper=false)
public class CasAuthUser extends User {

    private String id;

    private String booknumber;

    private String status;

    private String email;

    private String orgCode;

    private String name;

    private String creditCode;

    private String booktype;

    private String companyName;

    private String mobile;

    public CasAuthUser(String username, Collection<? extends GrantedAuthority> authorities) {
        super(username, "", authorities);
    }
}
