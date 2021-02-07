package com.mangolost.crud.sqlserver.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class SqlserverConfig {

    @Primary
    @Bean("sqlServerLocalDatasource")
    @ConfigurationProperties(prefix = "sqlserver.local")
    public DataSource sqlServerLocalDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("sqlServerLocalTemplate")
    public JdbcTemplate sqlServerLocalTemplate(@Qualifier("sqlServerLocalDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
