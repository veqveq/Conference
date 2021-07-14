package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.veqveq.conference.dto.TalkDto;
import ru.veqveq.conference.facades.UsersTalksFacade;

import java.security.Principal;


@RestController
@RequestMapping("api/v1/talks")
@RequiredArgsConstructor
public class TalkController {
    private final UsersTalksFacade usersTalksFacade;

    @PostMapping
    public void update(Principal principal, @RequestBody TalkDto talkDto) {
        usersTalksFacade.update(principal, talkDto);
    }

    @DeleteMapping
    public void delete(Principal principal, @RequestBody TalkDto talkDto) {
        usersTalksFacade.remove(principal, talkDto);
    }
}
