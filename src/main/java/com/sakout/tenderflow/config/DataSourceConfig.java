package com.sakout.tenderflow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "com.sakout.tenderflow.repository", entityManagerFactoryRef = "tenderFlowEntityManager", transactionManagerRef = "TransactionManager")
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "tender-flow.datasource")
    public DataSource tenderFlowDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Autowired
    public PlatformTransactionManager TransactionManager(@Qualifier("tenderFlowEntityManager") LocalContainerEntityManagerFactoryBean em) {
        return new JpaTransactionManager(Objects.requireNonNull(em.getObject()));
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean tenderFlowEntityManager(Environment env) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(tenderFlowDataSource());
        em.setPackagesToScan("com.sakout.tenderflow.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("tender-flow.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("tender-flow.jpa.database-platform"));
        properties.put("hibernate.show_sql", env.getProperty("tender-flow.jpa.properties.hibernate.show_sql"));
        properties.put("hibernate.use_sql_comments", env.getProperty("tender-flow.jpa.properties.hibernate.use_sql_comments"));
        properties.put("hibernate.format_sql", env.getProperty("tender-flow.jpa.properties.hibernate.format_sql"));
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        em.setJpaPropertyMap(properties);
        return em;
    }

}
