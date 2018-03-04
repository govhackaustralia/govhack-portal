package org.govhack.portal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.YEAR;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/resources/", "classpath:resources")
                .setCachePeriod(YEAR).setCacheControl(CacheControl.maxAge(YEAR, TimeUnit.SECONDS).cachePublic());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(86400 * 365);
        interceptor.setCacheControl(CacheControl.maxAge(YEAR, TimeUnit.SECONDS).cachePublic());
        registry.addInterceptor(interceptor).addPathPatterns("/resources/**");
    }

}
