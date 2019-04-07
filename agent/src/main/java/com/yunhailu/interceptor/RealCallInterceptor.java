package com.yunhailu.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RealCallInterceptor implements InstanceMethodsAroundInterceptor, InstanceConstructorInterceptor  {

    private static final Logger logger = LoggerFactory.getLogger(RealCallInterceptor.class);

    @Override
    public void onConstruct(EnhancedInstance objInst, Object[] allArguments) {
        objInst.setSkyWalkingDynamicField(allArguments[1]);
    }

    /**
     * Get the {@link okhttp3.Request} from {@link EnhancedInstance}, then create {@link AbstractSpan} and set host,
     * port, kind, component, url from {@link okhttp3.Request}.
     * Through the reflection of the way, set the http header of context data into {@link okhttp3.Request#headers}.
     *
     * @param method
     * @param result change this result, if you want to truncate the method.
     * @throws Throwable
     */
    @Override public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
                                       Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
        //TODO
        System.out.println("+++++++++++++++++++++++++++++++++beforeMethod");
    }

    /**
     * Get the status code from {@link Response}, when status code greater than 400, it means there was some errors in
     * the server.
     * Finish the {@link AbstractSpan}.
     *
     * @param method
     * @param ret the method's original return value.
     * @return
     * @throws Throwable
     */
    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
                              Class<?>[] argumentsTypes, Object ret) throws Throwable {
        System.out.println("+++++++++++++++++++afterMethod");

        return ret;
    }

    @Override public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments,
                                                Class<?>[] argumentsTypes, Throwable t) {
        System.out.println("++++++++++++++++++++++++++handleMethodException");
    }

}
