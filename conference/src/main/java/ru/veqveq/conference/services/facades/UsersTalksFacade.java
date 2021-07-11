package ru.veqveq.conference.services.facades;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.services.TalkService;
import ru.veqveq.conference.services.UserService;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UsersTalksFacade {
    private final UserService userService;
    private final TalkService talkService;

    @Transactional
    public void subscribeAsListener(Principal principal, Long talkId) {
        try {
            String name = principal.getName();
            User user = userService.findByLogin(name);
            Talk talk = talkService.findById(talkId);
            talk.addListener(user);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
