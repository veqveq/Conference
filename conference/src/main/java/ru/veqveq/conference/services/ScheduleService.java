package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.RoomDto;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<RoomDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleItem::getRoom)
                .distinct()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    public List<ScheduleItem> findAllBySpeaker(User speaker) {
        return scheduleRepository.findAllByTalk_Speakers(speaker);
    }

    public void save(ScheduleItem scheduleItem) {
        scheduleRepository.save(scheduleItem);
    }

    public Optional<ScheduleItem> findById(Long id) {
        return scheduleRepository.findById(id);
    }
}
