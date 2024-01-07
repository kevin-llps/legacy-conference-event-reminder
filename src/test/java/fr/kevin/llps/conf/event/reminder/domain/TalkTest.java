package fr.kevin.llps.conf.event.reminder.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.kevin.llps.conf.event.reminder.samples.TalkSample.oneTalk;
import static org.assertj.core.api.Assertions.assertThat;

class TalkTest {

    @Test
    void shouldGetCsvColumns() {
        Talk talk = oneTalk();

        List<String> expectedCsvColumns = List.of(
                "AWS Cognito",
                "Talk",
                "Après 2 ans à travailler sur la mise en place de cette solution au PMU, kevin llps nous présentera son retour d'expérience en détaillant les points forts et les points faibles de Cognito.",
                "13/10/2022",
                "19:45:00",
                "kevin llps",
                "",
                "");

        assertThat(talk.getCsvColumns()).containsExactlyElementsOf(expectedCsvColumns);
    }

}
