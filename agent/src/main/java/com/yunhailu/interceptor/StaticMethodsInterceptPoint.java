package com.yunhailu.interceptor;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

public interface StaticMethodsInterceptPoint {

    /**
     * static methods matcher.
     *
     * @return matcher instance.
     */
    ElementMatcher<MethodDescription> getMethodsMatcher();

    /**
     * @return represents a class name, the class instance must instanceof StaticMethodsAroundInterceptor.
     */
    String getMethodsInterceptor();

    boolean isOverrideArgs();

}
