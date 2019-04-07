package com.yunhailu.interceptor;

public interface InstanceConstructorInterceptor {

    /**
     * Called after the origin constructor invocation.
     */
    void onConstruct(EnhancedInstance objInst, Object[] allArguments);

}
