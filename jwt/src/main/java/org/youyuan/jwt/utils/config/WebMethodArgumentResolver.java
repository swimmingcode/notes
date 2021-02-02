package org.youyuan.jwt.utils.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.youyuan.jwt.utils.jwt.Token;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 19:03
 */
public class WebMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Token.class)
                && parameter.hasParameterAnnotation(org.youyuan.jwt.utils.jwt.annotation.Token.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return ((Token) webRequest.getAttribute("Token", RequestAttributes.SCOPE_REQUEST));
    }

    //@Component
    //public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    //    @Override
    //    public boolean supportsParameter(MethodParameter parameter) {
    //        return parameter.getParameterType().isAssignableFrom(User.class)
    //            && parameter.hasParameterAnnotation(CurrentUser.class);
    //    }
    //
    //    @Override
    //    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    //        return  (User) webRequest.getAttribute("CURRENT_USER",RequestAttributes.SCOPE_REQUEST);
    //    }
    //
    //}
}
