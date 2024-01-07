package fr.kevin.llps.conf.event.reminder.service;

import fr.kevin.llps.conf.event.reminder.domain.Talk;
import fr.kevin.llps.conf.event.reminder.service.database.MySQLDatabaseService;
import fr.kevin.llps.conf.event.reminder.service.database.mapper.TalkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TalkService {

    private static final String SELECT_TALKS = "SELECT t.date, t.title, " +
            "t.description, s.firstname, s.lastname " +
            "FROM talk t JOIN speaker s " +
            "ON t.speaker_id = s.speaker_id " +
            "ORDER BY t.date,t.title;";

    private final MySQLDatabaseService databaseService;
    private final TalkMapper talkMapper;

    public List<Talk> getTalks() {
        try (Connection connection = databaseService.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TALKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            return talkMapper.mapToDomainList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
