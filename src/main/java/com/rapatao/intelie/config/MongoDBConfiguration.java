package com.rapatao.intelie.config;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.rapatao.intelie")
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    protected String getDatabaseName() {
        return "desafio";
    }

    public Mongo mongo() throws Exception {
        final Fongo fongo = new Fongo("intellie-desafio-java");
        return fongo.getMongo();
    }
}
