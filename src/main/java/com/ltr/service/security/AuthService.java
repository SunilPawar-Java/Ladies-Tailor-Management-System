package com.ltr.service.security;

import com.ltr.exception.UserNotFoundException;
import com.ltr.model.security.UserSecurityDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() == null)
            throw new UserNotFoundException("Username Not Found In Authentication");
        return authentication.getName();
    }

    public Long getUserId(){
        Long id = ((UserSecurityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        if (id == null)
            throw new UserNotFoundException("User Id Not Found In Authentication");
        return id;
    }
}
