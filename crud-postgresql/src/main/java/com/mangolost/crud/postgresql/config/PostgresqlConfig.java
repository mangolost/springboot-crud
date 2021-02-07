package com.mangolost.crud.postgresql.config;

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
public class PostgresqlConfig {

    @Primary
    @Bean("postgresqlLocalDatasource")
    @ConfigurationProperties(prefix = "postgresql.local")
    public DataSource postgresqlLocalDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("postgresqlLocalTemplate")
    public JdbcTemplate postgresqlLocalTemplate(@Qualifier("postgresqlLocalDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
