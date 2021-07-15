package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.veqveq.conference.dto.ChangeRoleDto;
import ru.veqveq.conference.dto.RoleDto;
import ru.veqveq.conference.dto.UserDtoReq;
import ru.veqveq.conference.dto.UserDtoResp;
import ru.veqveq.conference.exceptions.ChangeRoleException;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.facades.UsersSchedulesFacade;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.services.RoleService;
import ru.veqveq.conference.services.UserService;
import ru.veqveq.conference.facades.UsersRolesFacade;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UsersRolesFacade usersRolesFacade;
    private final UsersSchedulesFacade usersSchedulesFacade;

    @GetMapping
    public List<UserDtoResp> findAll() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<RoleDto> findAllRoles() {
        return roleService.findAll();
    }

    @PostMapping("/roles")
    public void changeRole(Principal principal, @RequestBody ChangeRoleDto changeRoleDto) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User by username: " + principal.getName() + " not exist"));
        if (user.getId().equals(changeRoleDto.getUserId())) {
            throw new ChangeRoleException("Changing your own role is prohibited");
        }
        usersRolesFacade.changeRole(changeRoleDto);
    }

    @GetMapping("/speakers")
    public List<UserDtoResp> findAllSpeakers() {
        return userService.findAllByRole("ROLE_SPEAKER");
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long userId) {
        usersSchedulesFacade.cleanSpeakerWithSchedule(userId);
        userService.remove(userId);
    }

    @PutMapping
    public void createUser(@RequestBody UserDtoReq userDtoReq) {
        usersRolesFacade.createUser(userDtoReq);
    }

}
