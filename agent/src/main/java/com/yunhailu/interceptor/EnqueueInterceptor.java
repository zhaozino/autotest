package com.yunhailu.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class EnqueueInterceptor implements InstanceMethodsAroundInterceptor, InstanceConstructorInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(EnqueueInterceptor.class);

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes,
                             MethodInterceptResult result) throws Throwable {
        //TODO
        System.out.println("++++++++++++++++++++++beforeMethod");
    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes,
                              Object ret) throws Throwable {
        System.out.println("++++++++++++++++++++++afterMethod");
        return ret;
    }

    @Override public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments,
                                                Class<?>[] argumentsTypes, Throwable t) {
        System.out.println("+++++++++++++++++++++++++++++handleMethodException");
    }

    @Override public void onConstruct(EnhancedInstance objInst, Object[] allArguments) {
        objInst.setSkyWalkingDynamicField(allArguments[1]);
    }
}
