package org.govhack.portal;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.apache.catalina.core.JreMemoryLeakPreventionListener;
import org.apache.catalina.core.ThreadLocalLeakPreventionListener;
import org.apache.catalina.security.SecurityConfig;
import org.apache.catalina.security.SecurityListener;
import org.apache.catalina.startup.VersionLoggerListener;
import org.apache.commons.lang3.StringUtils;
import org.govhack.portal.config.PropertyPlaceholderConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.govhack.portal.config.JPAConfig;
import org.govhack.portal.config.ViewConfig;
import org.govhack.portal.config.WebConfig;

import javax.security.auth.message.config.AuthConfigFactory;
import java.util.Objects;

@Configuration

@Import(value = {
        // Boot auto conf. We opt to manually include the small parts we need,
        // rather than have boot magically configure tons of bs
        DispatcherServletAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class,
        ServerPropertiesAutoConfiguration.class,

        PropertyPlaceholderConfiguration.class,
        JPAConfig.class,
        SecurityConfig.class,
        ViewConfig.class,
        WebConfig.class
})

@EnableTransactionManagement
@ComponentScan(basePackages = {"org.govhack.portal"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PortalApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PortalApplication.class);

    public static void main(String[] args) {
        //Seems to be necessary to get any auth working
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        SpringApplication.run(PortalApplication.class, args);
    }

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addContextLifecycleListeners(new VersionLoggerListener());
        tomcat.addContextLifecycleListeners(new SecurityListener());
        tomcat.addContextLifecycleListeners(new JreMemoryLeakPreventionListener());
        tomcat.addContextLifecycleListeners(new ThreadLocalLeakPreventionListener());

        tomcat.addErrorPages(
                new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                new ErrorPage(HttpStatus.FORBIDDEN, "/unauthorised"),
                new ErrorPage(HttpStatus.UNAUTHORIZED, "/unauthorised"),
                new ErrorPage("/error"));

        return tomcat;
    }
}
