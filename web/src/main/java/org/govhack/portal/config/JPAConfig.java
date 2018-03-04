package org.govhack.portal.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("org.govhack")
@EnableTransactionManagement
public class JPAConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JPAConfig.class);

    @Bean(name = "govhackData")
    public DataSource primaryDataSource(@Value("${datasource.maxLifetime}") Long maxLifetime,
                                        @Value("${datasource.username}") String user,
                                        @Value("${datasource.password}") String password,
                                        @Value("${datasource.url}") String dataSourceUrl,
                                        @Value("${datasource.dataSourceClassName}") String dataSourceClassName,
                                        @Value("${datasource.connectionTimeout}") Long connectionTimeout) {
        LOG.info("Configuring HikariCP datasource for {}", dataSourceUrl);
        HikariConfig hc = new HikariConfig();
        hc.setMaximumPoolSize(10);
        hc.setConnectionTimeout(connectionTimeout);
        hc.setDataSourceClassName(dataSourceClassName);
        hc.setJdbcUrl(dataSourceUrl);
        hc.setUsername(user);
        hc.setPassword(password);
        hc.setMaxLifetime(maxLifetime);

        //to pass to the underlying datasource
        Properties dsProperties = new Properties();
        dsProperties.setProperty("user", user);
        dsProperties.setProperty("password", password);
        dsProperties.setProperty("url", dataSourceUrl);
        hc.setDataSourceProperties(dsProperties);

        return new HikariDataSource(hc);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("govhackData") DataSource ds) {
        LocalContainerEntityManagerFactoryBean b = new LocalContainerEntityManagerFactoryBean();
        b.setDataSource(ds);
        b.setPackagesToScan("org.govhack");
        b.setJpaDialect(new HibernateJpaDialect());
        HashMap<String, String> x = new HashMap<>();
        x.put("hibernate.cache.region.factory_class", org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory.class.getCanonicalName());
        x.put("net.sf.ehcache.configurationResourceName", "/ehcache.xml");
        b.setJpaPropertyMap(x);

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(false);
        adapter.setGenerateDdl(false);
        adapter.setDatabase(Database.POSTGRESQL);

        b.setJpaVendorAdapter(adapter);
        return b;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
