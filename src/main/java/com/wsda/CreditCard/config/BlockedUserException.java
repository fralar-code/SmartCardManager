package com.wsda.CreditCard.config;

import org.springframework.security.core.AuthenticationException;

public class BlockedUserException extends AuthenticationException {
    public BlockedUserException(String msg) {
        super(msg);
    }
}
