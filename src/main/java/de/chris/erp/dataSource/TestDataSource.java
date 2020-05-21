package de.chris.erp.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Konfiguration für die Test-Datenbank
 */
@Configuration
@Component
@Profile("testdb")
public class TestDataSource implements DataSource
{
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Profile("testdb")
    @Override
    public javax.sql.DataSource getDataSource()
    {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/erptest")
                .username("user")
                .password("password")
                .build();
    }
}
