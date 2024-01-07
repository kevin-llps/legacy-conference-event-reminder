package fr.kevin.llps.conf.event.reminder.service.database;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MySQLDatabaseService {

    private final DatabaseSecret databaseSecret;

    public Connection openConnection() {
        Connection connection = null;

        try {
            String url = "jdbc:" + databaseSecret.engine() + "://" + databaseSecret.host() + ":" + databaseSecret.port() + "/" +
                    databaseSecret.dbname();

            connection = DriverManager.getConnection(url, databaseSecret.username(), databaseSecret.password());
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e2) {
                    log.error("Impossible to close connection", e2);
                }
            }

            throw new RuntimeException(e);
        }
    }


}
