package ru.veqveq.conference.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.veqveq.conference.dto.ScheduleItemReq;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.TalkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = TalkService.class)
public class TalkServiceTest {
    @Autowired
    private TalkService talkService;

    @MockBean
    private TalkRepository mockTalkRepository;

    @Test
    public void findAllTest() {
        Talk talk = new Talk();
        talk.setId(10L);
        talk.setText("Text");
        Mockito
                .doReturn(Optional.of(talk))
                .when(mockTalkRepository).findById(10L);
        Assertions.assertEquals("Text", talkService.findById(10L).get().getText());
        Mockito.verify(mockTalkRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(10L));
    }

    @Test
    public void updateTest() {
        List<User> users = new ArrayList<>();
        Talk talk = new Talk();
        talk.setId(10L);
        ScheduleItemReq scheduleItemReq = new ScheduleItemReq();
        scheduleItemReq.setTalkId(10L);
        scheduleItemReq.setText("Text");
        Mockito
                .doReturn(Optional.of(talk))
                .when(mockTalkRepository).findById(10L);
        Assertions.assertEquals("Text", talkService.update(scheduleItemReq, users).getText());
        Mockito.verify(mockTalkRepository, Mockito.times(1)).save(ArgumentMatchers.any());
        Mockito.verify(mockTalkRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
    }

    @Test
    public void saveTest() {
        User owner = new User();
        List<User> users = new ArrayList<>();
        ScheduleItemReq scheduleItemReq = new ScheduleItemReq();
        scheduleItemReq.setText("Text");

        Assertions.assertEquals("Text", talkService.save(scheduleItemReq,users,owner).getText());
        Mockito.verify(mockTalkRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }


}
