package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.veqveq.conference.dto.ChangeRoleDto;
import ru.veqveq.conference.dto.UserDto;
import ru.veqveq.conference.exceptions.ChangeRoleException;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.services.UserService;
import ru.veqveq.conference.services.facades.UsersRolesFacade;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UsersRolesFacade usersRolesFacade;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public void changeRole(Principal principal, @RequestBody ChangeRoleDto changeRoleDto) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User by username: " + principal.getName() + " not exist"));
        if (user.getId().equals(changeRoleDto.getUserId())) {
            throw new ChangeRoleException("Changing your own role is prohibited");
        }
        usersRolesFacade.changeRole(changeRoleDto);
    }

    @GetMapping("/speakers")
    public List<UserDto> findAllSpeakers() {
        return userService.findAllByRole("ROLE_SPEAKER");
    }

}
