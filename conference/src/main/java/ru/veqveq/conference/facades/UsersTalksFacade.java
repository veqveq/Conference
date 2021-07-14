package ru.veqveq.conference.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.TalkDto;
import ru.veqveq.conference.dto.UserDto;
import ru.veqveq.conference.exceptions.IncorrectOwnerException;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
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
    public void remove(Principal principal, TalkDto talkDto) {
        if (!checkOwner(principal, talkDto))
            throw new IncorrectOwnerException("Insufficient rights to edit the conference");
        talkService.remove(talkDto.getId());
    }

    private boolean checkOwner(Principal principal, User owner) {
        User principalUser = userService.findByLogin(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User by id: %s not found", principal.getName())));
        return principalUser.equals(owner);
    }

    private boolean checkOwner(Principal principal, TalkDto talkDto) {
        User owner = talkService.findById(talkDto.getId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Talk by id: %s not found", talkDto.getId()))).getOwner();
        return checkOwner(principal, owner);
    }

    private void updateSpeakerList(Talk talk, TalkDto talkDto) {
        talk.getSpeakers().clear();
        for (UserDto userDto : talkDto.getSpeakers()) {
            User newUser = userService.findByLogin(userDto.getLogin())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("User by id: %s not found", userDto.getLogin())));
            talk.getSpeakers().add(newUser);
        }
    }
}
