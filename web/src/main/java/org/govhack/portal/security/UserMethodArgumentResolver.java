package org.govhack.portal.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserMethodArgumentResolver<T extends IGovhackUser> implements HandlerMethodArgumentResolver {

    private final Class<T> userClass;

    public UserMethodArgumentResolver(Class<T> userClass) {
        this.userClass = userClass;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(Authenticated.class) != null
                && methodParameter.getParameterType().equals(userClass);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        if (this.supportsParameter(methodParameter)) {
            return ((GovhackUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }


}