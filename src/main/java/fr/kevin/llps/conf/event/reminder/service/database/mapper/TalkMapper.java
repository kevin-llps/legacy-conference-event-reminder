package fr.kevin.llps.conf.event.reminder.service.database.mapper;

import fr.kevin.llps.conf.event.reminder.domain.Speaker;
import fr.kevin.llps.conf.event.reminder.domain.Talk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TalkMapper {

    public List<Talk> mapToDomainList(ResultSet resultSet) throws SQLException {
        List<Talk> talks = new ArrayList<>();

        while (resultSet.next()) {
            talks.add(mapToDomain(resultSet));
        }

        return talks;
    }

    private Talk mapToDomain(ResultSet resultSet) throws SQLException {
        LocalDateTime date = LocalDateTime.parse(resultSet.getString(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String title = resultSet.getString(2);
        String description = resultSet.getString(3);
        String speakerFirstname = resultSet.getString(4);
        String speakerLastname = resultSet.getString(5);

        Speaker speaker = new Speaker(speakerFirstname, speakerLastname);

        return new Talk(title, description, date, speaker);
    }

}
