package fr.kevin.llps.conf.event.reminder.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Talk(String title,
                   String description,
                   LocalDateTime date,
                   Speaker speaker) {

    private static final String EVENT_TYPE = "Talk";

    public String[] getCsvColumns() {
        return new String[]{
                title,
                EVENT_TYPE,
                description,
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                date.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                String.format("%s %s", speaker.firstname(), speaker.lastname()),
                "", ""};
    }

}
