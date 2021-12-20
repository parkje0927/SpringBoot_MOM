package com.example.websockectandconcurrentlogin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

public class CustomConcurrentSessionStrategy extends ConcurrentSessionControlAuthenticationStrategy {

    /**
     * @param sessionRegistry the session registry which should be updated when the
     *                        authenticated session is changed.
     */
    public CustomConcurrentSessionStrategy(SessionRegistry sessionRegistry) {
        super(sessionRegistry);
    }

    @Override
    protected int getMaximumSessionsForThisUser(Authentication authentication) {
        return super.getMaximumSessionsForThisUser(authentication);
    }
}
