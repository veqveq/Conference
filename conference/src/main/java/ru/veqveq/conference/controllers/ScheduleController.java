package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.veqveq.conference.dto.ScheduleItemDto;
import ru.veqveq.conference.dto.TalkDto;
import ru.veqveq.conference.services.UserService;
import ru.veqveq.conference.services.facades.UsersSchedulesFacade;
import ru.veqveq.conference.services.ScheduleService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final UsersSchedulesFacade schedulesFacade;

    @GetMapping
    public List<ScheduleItemDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/my_speaks")
    public List<TalkDto> getMySpeaks(Principal principal) {
        return schedulesFacade.getSpeaks(principal);
    }

    @GetMapping("/my_talks")
    public List<ScheduleItemDto> getMyTalks(Principal principal) {
        return schedulesFacade.getTalks(principal);
    }

    @PostMapping("/sub_as_listener")
    public void subscribeAsListener(Principal principal, @RequestParam Long talkId) {
        schedulesFacade.subscribeAsListener(principal, talkId);
    }

    @PostMapping("/unsub_as_listener")
    public void unsubscribeAsListener(Principal principal, @RequestParam Long talkId) {
        schedulesFacade.unsubscribeAsListener(principal, talkId);
    }
}
