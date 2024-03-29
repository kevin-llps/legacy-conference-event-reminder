package fr.kevin.llps.conf.event.reminder.api.rest;

import fr.kevin.llps.conf.event.reminder.api.rest.dto.TalkDto;
import fr.kevin.llps.conf.event.reminder.api.rest.mapper.TalkDtoMapper;
import fr.kevin.llps.conf.event.reminder.domain.Talk;
import fr.kevin.llps.conf.event.reminder.service.TalkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static fr.kevin.llps.conf.event.reminder.samples.TalkDtoSample.talkDtoList;
import static fr.kevin.llps.conf.event.reminder.samples.TalkSample.talkList;
import static fr.kevin.llps.conf.event.reminder.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TalkController.class)
class TalkControllerTest {

    @Value("classpath:/json/allEvents.json")
    private Resource events;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TalkService talkService;

    @MockBean
    private TalkDtoMapper talkMapper;

    @Test
    void shouldGetTalks() throws Exception {
        List<Talk> talks = talkList();
        List<TalkDto> talkDtoList = talkDtoList();

        when(talkService.getTalks()).thenReturn(talks);
        when(talkMapper.mapToDtoList(talks)).thenReturn(talkDtoList);

        mockMvc.perform(get("/talks"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(events)));

        verify(talkService).getTalks();
        verify(talkMapper).mapToDtoList(talks);

        verifyNoMoreInteractions(talkService, talkMapper);
    }

}
