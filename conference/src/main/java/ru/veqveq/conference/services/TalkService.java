package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.ScheduleItemReq;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.TalkRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TalkService {
    private final TalkRepository talkRepository;

    public Optional<Talk> findById(Long id) {
        return talkRepository.findById(id);
    }

    public Talk update(ScheduleItemReq scheduleItemReq, List<User> speakers) {
        Talk talk = findById(scheduleItemReq.getTalkId()).orElseThrow(() -> new ResourceNotFoundException("Talk by id: " + scheduleItemReq.getTalkId() + " not found"));
        talk.setText(scheduleItemReq.getText());
        talk.setSpeakers(speakers);
        talkRepository.save(talk);
        return talk;
    }

    public Talk save(ScheduleItemReq scheduleItemReq, List<User> speakers, User owner) {
        Talk talk = new Talk.Builder()
                .setOwner(owner)
                .setText(scheduleItemReq.getText())
                .setSpeakers(speakers)
                .build();
        talkRepository.save(talk);
        return talk;
    }
}
