package com.yunhailu;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtectiveShieldMatcher<T> extends ElementMatcher.Junction.AbstractBase<T>{

    private static final Logger logger = LoggerFactory.getLogger(ProtectiveShieldMatcher.class);

    private final ElementMatcher<? super T> matcher;

    public ProtectiveShieldMatcher(ElementMatcher<? super T> matcher) {
        this.matcher = matcher;
    }

    public boolean matches(T target) {
        try {
            return this.matcher.matches(target);
        } catch (Throwable t) {
            logger.warn("Byte-buddy occurs exception when match type.", t);
            return false;
        }
    }
}
