package com.ltr.model.security;

import com.ltr.model.Users;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {

    public final Users user;

    public UserSecurityDetails(Users user) {
        this.user = user;
        this.user.setOrders(null);
    }

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    @NonNull
    public String getUsername() {
        return user.getUsername();
    }

    public Long getUserId(){
        return user.getId();
    }

    public String getPhone(){
        return user.getPhone();
    }

    public String getEmail(){
        return user.getEmail();
    }
}
