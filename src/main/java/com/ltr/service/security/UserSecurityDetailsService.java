package com.ltr.service.security;

import com.ltr.model.security.UserSecurityDetails;
import com.ltr.service.UsersService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityDetailsService implements UserDetailsService {
    private final UsersService usersService;

    public UserSecurityDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String identity) throws UsernameNotFoundException {
        return new UserSecurityDetails(usersService.getUserByUsernameOrEmailOrPhone(identity));
    }
}