package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.ScheduleItemDto;
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
}
