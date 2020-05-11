package de.chris.erp.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DevDataSource implements DataSource
{
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Override
    public javax.sql.DataSource getDataSource()
    {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/erp")
                .username("user")
                .password("password")
                .build();
    }
}
