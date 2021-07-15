package ru.veqveq.conference.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.ScheduleItemReq;
import ru.veqveq.conference.exceptions.IncorrectOwnerException;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
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
    public void createSchedule(Principal principal, ScheduleItemReq scheduleItemReq) {
        List<User> speakers = scheduleItemReq
                .getSpeakers()
                .stream()
                .map(id -> userService.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User by login: " + principal.getName() + " does not exist")))
                .collect(Collectors.toList());

        User owner = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User by login: " + principal.getName() + " not exist"));

        Room room = roomService.findByNumber(scheduleItemReq.getRoom())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Room by number: " + scheduleItemReq.getRoom() + " does not exist"));

        Talk talk = talkService.save(scheduleItemReq, speakers, owner);

        scheduleService.save(scheduleItemReq, room, talk);
    }

    @Transactional
    public void updateSchedule(ScheduleItemReq scheduleItemReq) {
        List<User> speakers = scheduleItemReq.getSpeakers().stream().map(userId -> userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User by id: " + userId + " not found"))).collect(Collectors.toList());
        Talk talk = talkService.update(scheduleItemReq, speakers);
        Room room = roomService.findByNumber(scheduleItemReq.getRoom()).orElseThrow(() -> new ResourceNotFoundException("Room by number: " + scheduleItemReq.getRoom() + " not found"));
        scheduleService.update(scheduleItemReq, talk, room);
    }

    @Transactional
    public void remove(Principal principal, Long talkId) {
        if (!checkOwner(principal, talkId))
            throw new IncorrectOwnerException("Insufficient rights to edit the conference");
        scheduleService.remove(talkId);
    }

    private boolean checkOwner(Principal principal, User owner) {
        User principalUser = userService.findByLogin(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User by id: %s not found", principal.getName())));
        return principalUser.equals(owner);
    }

    private boolean checkOwner(Principal principal, Long talkId) {
        User owner = talkService.findById(talkId).orElseThrow(() -> new ResourceNotFoundException(String.format("Talk by id: %s not found", talkId))).getOwner();
        return checkOwner(principal, owner);
    }
}
