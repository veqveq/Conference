package ru.veqveq.conference.services.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.ScheduleItemDto;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.exceptions.TimeIntervalIntersectionException;
import ru.veqveq.conference.models.*;
import ru.veqveq.conference.services.RoomService;
import ru.veqveq.conference.services.ScheduleService;
import ru.veqveq.conference.services.TalkService;
import ru.veqveq.conference.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatorScheduleFacade {
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final TalkService talkService;
    private final RoomService roomService;

    @Transactional
    public void createSchedule(Principal principal, ScheduleItemDto scheduleItemDto) {

        List<User> speakers = scheduleItemDto
                .getTalkDto()
                .getSpeakers()
                .stream()
                .map(userDto ->
                        userService.findByLogin(
                                userDto.getLogin())
                                .orElseThrow(
                                        () -> new ResourceNotFoundException("User by login: " + principal.getName() + " does not exist")))
                .collect(Collectors.toList());

        User owner = userService.findByLogin(
                principal.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User by login: " + principal.getName() + " not exist"));

        Room currentRoom = roomService.findByNumber(scheduleItemDto.getRoom())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Room by number: " + scheduleItemDto.getRoom() + " does not exist"));
        List<ScheduleItem> scheduleItems = currentRoom.getScheduleItemList();

        TimeInterval newInterval = new TimeInterval(scheduleItemDto.getStartTime(), scheduleItemDto.getEndTime());
        scheduleItems
                .stream()
                .map(scheduleItem -> new TimeInterval(scheduleItem.getStartTime(), scheduleItem.getEndTime()))
                .forEach((ti) -> {
                    if (ti.intersection(newInterval)) {
                        throw new TimeIntervalIntersectionException(String.format("Time %s is not a free", ti.toString()));
                    }
                });

        Talk newTalk = new Talk.Builder()
                .setText(scheduleItemDto.getTalkDto().getText())
                .setOwner(owner)
                .setSpeakers(speakers)
                .build();
        talkService.save(newTalk);

        ScheduleItem newScheduleItem = new ScheduleItem.Builder()
                .setRoom(currentRoom)
                .setTalk(newTalk)
                .setTimeInterval(newInterval)
                .build();

        scheduleService.save(newScheduleItem);
    }
}
