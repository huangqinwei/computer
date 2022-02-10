package com.huang.init;

import com.huang.config.ApplicationConfig;
import com.huang.config.MyMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Springapplicationinitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MyMvcConfig.class };
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
