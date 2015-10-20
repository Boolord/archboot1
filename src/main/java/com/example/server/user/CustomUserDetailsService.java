/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.server.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO dao;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = dao.findByUserName(string);
        if (user == null) {
            throw new UsernameNotFoundException("user with name " + string + " not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(authority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), list);
    }

    public void signin(User account) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(account));
    }

    private Authentication authenticate(User account) {
        return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
    }

    private org.springframework.security.core.userdetails.User createUser(User account) {
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), Collections.singleton(createAuthority(account)));
    }

    private GrantedAuthority createAuthority(User account) {
        return new SimpleGrantedAuthority(account.getRole());
    }

}
