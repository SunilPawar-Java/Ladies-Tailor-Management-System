package com.ltr.exception.classes;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
