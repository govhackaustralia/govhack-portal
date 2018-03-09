package org.govhack.portal.config;

import freemarker.cache.NullCacheStorage;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;

@Configuration
public class ViewConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ViewConfig.class);

    @Value("${freemarker.cache:true}")
    Boolean cache;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer x = new FreeMarkerConfigurer() {
            @Override
            public void afterPropertiesSet() throws IOException, TemplateException {
                super.afterPropertiesSet();
                freemarker.template.Configuration c = getConfig(this.getConfiguration());
                if (cache != null && !cache) {
                    LOG.warn("-=-=-=-=-=-=-=-=-=-=-=-=-=- DISABLING FREEMARKER CACHE  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    c.setCacheStorage(new NullCacheStorage());
                }
            }
        };
        x.setTemplateLoaderPaths("/resources/templates/", "classpath:templates");
        return x;
    }

    private static freemarker.template.Configuration getConfig(freemarker.template.Configuration c) throws TemplateModelException {
        c.setIncompatibleImprovements(freemarker.template.Configuration.VERSION_2_3_26);
        c.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        c.setAPIBuiltinEnabled(true); // sometimes you want direct access to underlying java objects
        c.setNumberFormat("0.######"); //no commas in numbers
        c.setDefaultEncoding("UTF-8");
        c.setOutputEncoding("UTF-8");
        c.setWhitespaceStripping(true);
        HTMLOutputFormat f = HTMLOutputFormat.INSTANCE;
        c.setOutputFormat(f);
        if (!f.isAutoEscapedByDefault()) {
            throw new IllegalStateException("We require our output format to be auto-escapable");
        }
        c.setAutoEscapingPolicy(freemarker.template.Configuration.ENABLE_IF_DEFAULT_AUTO_ESCAPING_POLICY);
        return c;
    }


    @Bean
    public FreeMarkerViewResolver getInternalResourceViewResolver() {
        FreeMarkerViewResolver x = new FreeMarkerViewResolver();
        x.setPrefix("");
        x.setSuffix(".ftl");
        x.setViewClass(FreeMarkerView.class);
        x.setRequestContextAttribute("rc");
        x.setExposeSpringMacroHelpers(true);
        x.setContentType("text/html;charset=UTF-8");
        return x;
    }
}
