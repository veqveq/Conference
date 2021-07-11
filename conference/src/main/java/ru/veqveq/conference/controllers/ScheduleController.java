package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veqveq.conference.dto.ScheduleItemDto;
import ru.veqveq.conference.dto.TalkDto;
import ru.veqveq.conference.facades.UsersSchedulesFacade;
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

    @GetMapping("/speaker/myTalks")
    public List<TalkDto> getMyTalks(Principal principal) {
        return schedulesFacade.getTalks(principal);
    }
}
