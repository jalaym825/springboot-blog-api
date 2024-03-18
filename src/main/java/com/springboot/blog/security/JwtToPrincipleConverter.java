package com.springboot.blog.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.springboot.blog.entities.Role;
import com.springboot.blog.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtToPrincipleConverter {
    public User convert(DecodedJWT jwt)
    {
       return User.builder()
               .id(Integer.parseInt(jwt.getSubject()))
               .email(jwt.getClaim("e").asString())
               .roles(jwt.getClaim("a").asList(Role.class)).build();
//        return User.builder
    }
}
