//package com.springboot.blog.security;
//
//import com.springboot.blog.entities.User;
//import com.springboot.blog.exceptions.ResourceNotFoundException;
//import com.springboot.blog.repositories.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "id", username));
//    }
//}
