package com.yunhailu.interceptor;

public abstract class ClassInstanceMethodsEnhancePluginDefine  extends ClassEnhancePluginDefine{

    /**
     * @return null, means enhance no static methods.
     */
    @Override
    protected StaticMethodsInterceptPoint[] getStaticMethodsInterceptPoints() {
        return null;
    }

}
