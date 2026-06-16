package com.ltr.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
