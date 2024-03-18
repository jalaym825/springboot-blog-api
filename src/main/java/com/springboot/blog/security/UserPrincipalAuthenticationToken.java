package com.springboot.blog.security;

import com.springboot.blog.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final User user;
    public UserPrincipalAuthenticationToken(User user) {
        super(user.getAuthorities());
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
