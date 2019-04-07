package com.yunhailu.interceptor;

import com.yunhailu.ClassMatch;
import com.yunhailu.NameMatch;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class RealCallInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {

    /**
     * Enhance class.
     */
    private static final String ENHANCE_CLASS = "okhttp3.RealCall";

    /**
     * Intercept class.
     */
    private static final String INTERCEPT_CLASS = "com.yunhailu.interceptor.RealCallInterceptor";

    @Override protected ClassMatch enhanceClass() {
        return NameMatch.byName(ENHANCE_CLASS);
    }

    @Override protected ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return new ConstructorInterceptPoint[] {
                new ConstructorInterceptPoint() {
                    @Override public ElementMatcher<MethodDescription> getConstructorMatcher() {
                        return any();
                    }

                    @Override public String getConstructorInterceptor() {
                        return INTERCEPT_CLASS;
                    }
                }
        };
    }

    @Override protected InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[] {
                new InstanceMethodsInterceptPoint() {
                    @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named("execute");
                    }

                    @Override public String getMethodsInterceptor() {
                        return INTERCEPT_CLASS;
                    }

                    @Override public boolean isOverrideArgs() {
                        return false;
                    }
                },
                new InstanceMethodsInterceptPoint() {
                    @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named("enqueue").and(takesArguments(1));
                    }

                    @Override public String getMethodsInterceptor() {
                        return "com.yunhailu.interceptor.EnqueueInterceptor";
                    }

                    @Override public boolean isOverrideArgs() {
                        return false;
                    }
                }
        };
    }

}
