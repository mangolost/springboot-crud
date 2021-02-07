package com.mangolost.crud.mysql.config;

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
public class MysqlConfig {

    @Primary
    @Bean("mysqlLocalDatasource")
    @ConfigurationProperties(prefix = "mysql.local")
    public DataSource mysqlLocalDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("mysqlLocalTemplate")
    public JdbcTemplate mysqlLocalTemplate(@Qualifier("mysqlLocalDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
