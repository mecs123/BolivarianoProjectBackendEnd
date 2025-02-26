package com.user.securityApp.exception;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.authorization.method.MethodAuthorizationDeniedHandler;
import org.springframework.security.authorization.method.MethodInvocationResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityErrorHandler implements MethodAuthorizationDeniedHandler {
    /**
     * @param methodInvocation    the {@link MethodInvocation} related to the authorization
     *                            denied
     * @param authorizationResult the authorization denied result
     * @return
     */
    @Override
    public Object handleDeniedInvocation(MethodInvocation methodInvocation, AuthorizationResult authorizationResult) {
        return null;
    }

    /**
     * @param methodInvocationResult the object containing the {@link MethodInvocation}
     *                               and the result produced
     * @param authorizationResult    the authorization denied result
     * @return
     */
    @Override
    public Object handleDeniedInvocationResult(
            MethodInvocationResult methodInvocationResult,
            AuthorizationResult authorizationResult
    ) {
        log.info("/n");
        log.info(String.format("Method info -> %s",
                methodInvocationResult.toString()));
        log.info(String.format("Is authorizaded -> %s",
                authorizationResult.isGranted()));

        return "+++++++";
    }
}
