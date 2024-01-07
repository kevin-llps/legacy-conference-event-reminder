package fr.kevin.llps.conf.event.reminder.service.database.mapper;

import fr.kevin.llps.conf.event.reminder.domain.Talk;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TalkMapperTest {

    @Test
    void shouldMapToDomainList() throws SQLException {
        TalkMapper talkMapper = new TalkMapper();

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn("2023-02-05 19:00:00").thenReturn("2023-06-28 19:00:00");
        when(resultSet.getString(2)).thenReturn("conf1").thenReturn("conf2");
        when(resultSet.getString(3)).thenReturn("Description conf1").thenReturn("Description conf2");
        when(resultSet.getString(4)).thenReturn("jean").thenReturn("alice");
        when(resultSet.getString(5)).thenReturn("dupont").thenReturn("dupont");

        List<Talk> talks = talkMapper.mapToDomainList(resultSet);

        assertThat(talks).isNotNull()
                .hasSize(2)
                .extracting("title", "description", "date", "speaker.firstname", "speaker.lastname")
                .containsExactlyInAnyOrder(
                        tuple("conf1",
                                "Description conf1",
                                LocalDateTime.of(2023, 2, 5, 19, 0, 0),
                                "jean", "dupont"),
                        tuple("conf2",
                                "Description conf2",
                                LocalDateTime.of(2023, 6, 28, 19, 0, 0),
                                "alice", "dupont"));
    }

}
