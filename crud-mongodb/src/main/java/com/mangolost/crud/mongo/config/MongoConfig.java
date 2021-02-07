package com.mangolost.crud.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 *
 */
@Configuration
public class MongoConfig {

    @Bean("mongoLocalDatabaseFactory")
    @Primary
    MongoDatabaseFactory mongoLocalDatabaseFactory(MongoLocalDatasourceProperties properties) {

        MongoClientSettings mongoClientSettings = MongoClientSettings
                .builder()
                .applyConnectionString(new ConnectionString(properties.getJdbcUrl()))
                .build();

        // 创建客户端和Factory
        MongoClient mongoClient = MongoClients.create(mongoClientSettings);

        return new SimpleMongoClientDatabaseFactory(mongoClient, properties.getDatabase());
    }

    @Bean("mongoLocalTemplate")
    @Primary
    public MongoTemplate mongoLocalTemplate(@Qualifier("mongoLocalDatabaseFactory") MongoDatabaseFactory mongoLocalDatabaseFactory) {

        // Remove _class
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoLocalDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return new MongoTemplate(mongoLocalDatabaseFactory, converter);
    }
}