package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.RoomDto;
import ru.veqveq.conference.dto.ScheduleItemReq;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.exceptions.TimeIntervalIntersectionException;
import ru.veqveq.conference.models.*;
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

    public void update(ScheduleItemReq scheduleItemReq, Talk talk, Room room) {
        ScheduleItem scheduleItem = scheduleRepository.findById(scheduleItemReq.getId()).orElseThrow(() -> new ResourceNotFoundException("Schedule by id: " + scheduleItemReq.getId() + " not found"));
        scheduleItem.setStartTime(scheduleItemReq.getStartTime());
        scheduleItem.setEndTime(scheduleItemReq.getEndTime());
        scheduleItem.setTalk(talk);
        scheduleItem.setRoom(room);
    }

    public void save(ScheduleItemReq scheduleItemReq, Room room, Talk talk) {
        TimeInterval interval = new TimeInterval(scheduleItemReq.getStartTime(), scheduleItemReq.getEndTime());
        checkInterval(interval, room);

        ScheduleItem scheduleItem = new ScheduleItem.Builder()
                .setRoom(room)
                .setTalk(talk)
                .setTimeInterval(interval)
                .build();

        scheduleRepository.save(scheduleItem);
    }

    private void checkInterval(TimeInterval interval, Room room) {
        List<ScheduleItem> scheduleItems = room.getScheduleItemList();
        scheduleItems
                .stream()
                .map(scheduleItem -> new TimeInterval(scheduleItem.getStartTime(), scheduleItem.getEndTime()))
                .forEach((ti) -> {
                    if (ti.intersection(interval)) {
                        throw new TimeIntervalIntersectionException(String.format("Time %s is not a free", ti.toString()));
                    }
                });
    }

    public Optional<ScheduleItem> findById(Long id) {
        return scheduleRepository.findById(id);
    }
}
