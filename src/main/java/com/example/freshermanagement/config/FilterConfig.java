package com.example.freshermanagement.config;

import com.example.freshermanagement.util.CustomRequestResponseLoggingFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public CustomRequestResponseLoggingFilter loggingFilter(){
        return new CustomRequestResponseLoggingFilter();
    }
}
