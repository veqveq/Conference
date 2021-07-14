package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.veqveq.conference.dto.RoomDto;
import ru.veqveq.conference.dto.ScheduleItemReq;
import ru.veqveq.conference.dto.ScheduleItemResp;
import ru.veqveq.conference.facades.CreatorScheduleFacade;
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
    private final CreatorScheduleFacade creatorFacade;

    @GetMapping
    public List<RoomDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/my_speaks")
    public List<ScheduleItemResp> getMySpeaks(Principal principal) {
        return schedulesFacade.getSpeaks(principal);
    }

    @GetMapping("/my_talks")
    public List<ScheduleItemResp> getMyTalks(Principal principal) {
        return schedulesFacade.getTalks(principal);
    }

    @PostMapping("/sub")
    public void subscribeAsListener(Principal principal, @RequestParam Long talkId) {
        schedulesFacade.subscribeAsListener(principal, talkId);
    }

    @PostMapping("/unsub")
    public void unsubscribeAsListener(Principal principal, @RequestParam Long talkId) {
        schedulesFacade.unsubscribeAsListener(principal, talkId);
    }

    @PutMapping("/my_speaks")
    public void createSchedule(Principal principal, @RequestBody ScheduleItemReq newSchedule) {
        creatorFacade.createSchedule(principal, newSchedule);
    }

    @PostMapping("/my_speaks")
    public void updateSchedule(@RequestBody ScheduleItemReq newSchedule) {
        creatorFacade.updateSchedule(newSchedule);
    }
}
