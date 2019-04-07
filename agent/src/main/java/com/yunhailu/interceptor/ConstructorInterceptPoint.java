package com.yunhailu.interceptor;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

public interface ConstructorInterceptPoint {

    /**
     * Constructor matcher
     *
     * @return matcher instance.
     */
    ElementMatcher<MethodDescription> getConstructorMatcher();

    /**
     * @return represents a class name, the class instance must be a instance of {@link
     * org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceConstructorInterceptor}
     */
    String getConstructorInterceptor();

}
