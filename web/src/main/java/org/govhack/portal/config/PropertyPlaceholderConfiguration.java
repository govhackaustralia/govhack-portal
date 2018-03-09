package org.govhack.portal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PropertyPlaceholderConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyPlaceholderConfiguration.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        LOG.info("**********Loading Properties************");
        System.out.println("**********Loading Properties************");

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));

        PropertySourcesPlaceholderConfigurer x = new PropertySourcesPlaceholderConfigurer();
        x.setPropertiesArray(yaml.getObject());
        return x;
    }

//    private static String getEnv(String envVar, String fallback) {
//        Object property = System.getenv(envVar);
//        return property == null ? fallback : String.valueOf(property);
//    }
//
//    private static Properties readResource(Resource classPathResource) {
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(classPathResource);
//        return yaml.getObject();
//    }

}