package org.govhack.portal.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PortalAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(PortalAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void fallback(Exception e, HttpServletRequest req) {
        LOG.error("{} - Error caught by global fallback", req.getRequestURL(), e);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void auth(AuthenticationException e, HttpServletRequest req) {
        LOG.info("{} - Error caught by global auth fallback", req.getRequestURL(), e);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void auth(AccessDeniedException e, HttpServletRequest req) {
        LOG.info("{} - Error caught by global auth fallback", req.getRequestURL(), e);
    }
}
