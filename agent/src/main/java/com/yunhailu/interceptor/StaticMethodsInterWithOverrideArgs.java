package com.yunhailu.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class StaticMethodsInterWithOverrideArgs {

    private static final Logger logger = LoggerFactory.getLogger(StaticMethodsInterWithOverrideArgs.class);

    /**
     * A class full name, and instanceof {@link StaticMethodsAroundInterceptor}
     * This name should only stay in {@link String}, the real {@link Class} type will trigger classloader failure.
     * If you want to know more, please check on books about Classloader or Classloader appointment mechanism.
     */
    private String staticMethodsAroundInterceptorClassName;

    /**
     * Set the name of {@link StaticMethodsInterWithOverrideArgs#staticMethodsAroundInterceptorClassName}
     *
     * @param staticMethodsAroundInterceptorClassName class full name.
     */
    public StaticMethodsInterWithOverrideArgs(String staticMethodsAroundInterceptorClassName) {
        this.staticMethodsAroundInterceptorClassName = staticMethodsAroundInterceptorClassName;
    }

    /**
     * Intercept the target static method.
     *
     * @param clazz target class
     * @param allArguments all method arguments
     * @param method method description.
     * @param zuper the origin call ref.
     * @return the return value of target static method.
     * @throws Exception only throw exception because of zuper.call() or unexpected exception in sky-walking ( This is a
     * bug, if anything triggers this condition ).
     */
    @RuntimeType
    public Object intercept(@Origin Class<?> clazz, @AllArguments Object[] allArguments, @Origin Method method,
                            @Morph OverrideCallable zuper) throws Throwable {
        StaticMethodsAroundInterceptor interceptor = InterceptorInstanceLoader
                .load(staticMethodsAroundInterceptorClassName, clazz.getClassLoader());

        MethodInterceptResult result = new MethodInterceptResult();
        try {
            interceptor.beforeMethod(clazz, method, allArguments, method.getParameterTypes(), result);
        } catch (Throwable t) {
            logger.error("fdsfdsa", t);
        }

        Object ret = null;
        try {
            if (!result.isContinue()) {
                ret = result._ret();
            } else {
                ret = zuper.call(allArguments);
            }
        } catch (Throwable t) {
            try {
                interceptor.handleMethodException(clazz, method, allArguments, method.getParameterTypes(), t);
            } catch (Throwable t2) {
                logger.error("fdsafsda", t2);
            }
            throw t;
        } finally {
            try {
                ret = interceptor.afterMethod(clazz, method, allArguments, method.getParameterTypes(), ret);
            } catch (Throwable t) {
                logger.error("fdsfdsa", t);
            }
        }
        return ret;
    }
}
