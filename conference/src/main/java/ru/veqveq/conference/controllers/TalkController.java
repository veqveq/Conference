package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.veqveq.conference.services.facades.UsersTalksFacade;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/talks")
@RequiredArgsConstructor
public class TalkController {
    private final UsersTalksFacade talksFacade;

    @PostMapping("/subscribe_as_listener")
    public void subscribeAsListener(Principal principal, @RequestParam Long talkId) {
        talksFacade.subscribeAsListener(principal, talkId);
    }
}
