package com.ai.emailagent.config;

import com.networknt.schema.resource.ClasspathResourceLoader;
import com.networknt.schema.resource.ResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceLoaderConfig {

    @Bean
    public ResourceLoader resourceLoader() {
        return new ClasspathResourceLoader();
    }

}
