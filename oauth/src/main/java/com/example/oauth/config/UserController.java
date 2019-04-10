package com.example.oauth.config;

import com.example.oauth.entity.User;
import com.example.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController implements UserDetailsService {
    @Autowired
    private UserService<User> userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user1 = userService.selectByName().get(0);
        GrantedAuthority oAuth2UserAuthority = new OAuth2UserAuthority(new HashMap<>());
        ArrayList<GrantedAuthority> objects = new ArrayList<>();
        objects.add(oAuth2UserAuthority);
        org.springframework.security.core.userdetails.User user =
                new org.springframework.security.core.userdetails.User(user1.getUserName(), user1.getPassword(),objects);
        return user;
    }
}
