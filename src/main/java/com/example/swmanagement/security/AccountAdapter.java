package com.example.swmanagement.security;

import com.example.swmanagement.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class AccountAdapter extends org.springframework.security.core.userdetails.User {

    private User users;

    public AccountAdapter(User user) {

        super(user.getUsername(), user.getPassword(), authorities());
    }

    private static Set<GrantedAuthority> authorities() {
        List<String> authorities = new ArrayList<>();
        authorities.add("abc");
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
    }
}
