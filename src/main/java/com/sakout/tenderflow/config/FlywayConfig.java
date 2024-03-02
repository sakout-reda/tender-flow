package com.sakout.tenderflow.config;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
    @Autowired
    @Qualifier("tenderFlowDataSource")
    DataSource tenderFlowDataSource;

    @PostConstruct
    public void migrateFlyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(tenderFlowDataSource)
                .locations("db")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
    }
}
