package fr.kevin.llps.conf.event.reminder.api.rest.mapper;

import fr.kevin.llps.conf.event.reminder.api.rest.dto.SpeakerDto;
import fr.kevin.llps.conf.event.reminder.api.rest.dto.TalkDto;
import fr.kevin.llps.conf.event.reminder.domain.Talk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TalkDtoMapper {

    private final SpeakerDtoMapper speakerMapper;

    public List<TalkDto> mapToDtoList(List<Talk> talks) {
        return talks.stream()
                .map(this::mapToDto)
                .toList();
    }

    private TalkDto mapToDto(Talk talk) {
        SpeakerDto speakerDto = speakerMapper.mapToDto(talk.speaker());

        return TalkDto.builder()
                .title(talk.title())
                .description(talk.description())
                .date(talk.date())
                .speaker(speakerDto)
                .build();
    }

}
