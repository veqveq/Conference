package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.ScheduleItemDto;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleItemDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleItemDto::new)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<ScheduleItem> findAllBySpeaker(User speaker) {
        return scheduleRepository.findAllByTalk_Speakers(speaker);
    }

    public List<ScheduleItem> findAllByListener(User listener) {
        return scheduleRepository.findAllByTalk_Listeners(listener);
    }
}
