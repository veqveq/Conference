package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.veqveq.conference.facades.UsersTalksFacade;
import ru.veqveq.conference.services.TalkService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/talks")
@RequiredArgsConstructor
public class TalkController {
    private final TalkService talkService;
    private final UsersTalksFacade talksFacade;

    @PostMapping("/becomeListener")
    public void becomeListener(Principal principal, @RequestParam Long talkId) {
        talksFacade.becomeListener(principal, talkId);
    }
}
