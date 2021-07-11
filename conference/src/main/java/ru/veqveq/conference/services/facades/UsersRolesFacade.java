package ru.veqveq.conference.services.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.NewRoleDto;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.models.Role;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.services.RoleService;
import ru.veqveq.conference.services.UserService;

@Service
@RequiredArgsConstructor
public class UsersRolesFacade {
    private final UserService userService;
    private final RoleService roleService;

    @Transactional
    public void changeRole(NewRoleDto newRoleDto) {
        User user = userService.findById(newRoleDto.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("User by id: %d not found", newRoleDto.getUserId())));
        Role newRole = roleService.findById(newRoleDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Role by id: %s not found", newRoleDto.getRoleId())));
        user.setRole(newRole);
        userService.save(user);
    }
}
