package fr.kevin.llps.conf.event.reminder.configuration;

import fr.kevin.llps.conf.event.reminder.service.database.DatabaseSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DatabaseSecret databaseSecret(@Value("${database.username}") String username,
                                         @Value("${database.password}") String password,
                                         @Value("${database.engine}") String engine,
                                         @Value("${database.dbname}") String dbname,
                                         @Value("${database.host}") String host,
                                         @Value("${database.port}") String port) {

        return new DatabaseSecret(username, password, engine, dbname, host, port);
    }

}
