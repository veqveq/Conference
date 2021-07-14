package ru.veqveq.conference.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.ChangeRoleDto;
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
    public void changeRole(ChangeRoleDto changeRoleDto) {
        User user = userService.findById(changeRoleDto.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("User by id: %d not found", changeRoleDto.getUserId())));
        Role newRole = roleService.findById(changeRoleDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Role by id: %s not found", changeRoleDto.getRoleId())));
        user.setRole(newRole);
        userService.saveOrUpdate(user);
    }
}
