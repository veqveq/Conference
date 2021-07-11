package ru.veqveq.conference.services.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.ScheduleItemDto;
import ru.veqveq.conference.dto.TalkDto;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.services.ScheduleService;
import ru.veqveq.conference.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersSchedulesFacade {
    private final UserService userService;
    private final ScheduleService scheduleService;

    @Transactional
    public List<TalkDto> getSpeaks(Principal principal) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User by name: %s not exist", principal.getName())));
        List<Talk> talks = scheduleService.findAllBySpeaker(user)
                .stream()
                .map(ScheduleItem::getTalk)
                .collect(Collectors.toList());
        return talks
                .stream()
                .map(TalkDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ScheduleItemDto> getTalks(Principal principal) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User by name: %s not exist", principal.getName())));
        return user.getTalksAsListener()
                .stream()
                .map(ScheduleItemDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void subscribeAsListener(Principal principal, Long scheduleId) {
        String name = principal.getName();
        User user = userService.findByLogin(name)
                .orElseThrow(() -> new ResourceNotFoundException("User by username: " + name + " not exist"));
        ScheduleItem talk = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Talk by id: " + scheduleId + " not exist"));
        talk.addListener(user);
    }

    @Transactional
    public void unsubscribeAsListener(Principal principal, Long scheduleId) {
        String name = principal.getName();
        User user = userService.findByLogin(name)
                .orElseThrow(() -> new ResourceNotFoundException("User by username: " + name + " not exist"));
        ScheduleItem talk = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Talk by id: " + scheduleId + " not exist"));
        talk.removeListener(user);
    }
}
