package ru.veqveq.conference.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.ChangeRoleDto;
import ru.veqveq.conference.dto.UserDtoReq;
import ru.veqveq.conference.dto.UserDtoResp;
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
    private final BCryptPasswordEncoder passwordEncoder;

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

    @Transactional
    public void createUser(UserDtoReq userDtoReq) {
        Role newRole = roleService.findById(userDtoReq.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Role by id: %s not found", userDtoReq.getRoleId())));
        User user = new User();
        user.setLogin(userDtoReq.getLogin());
        user.setPassword(passwordEncoder.encode(userDtoReq.getPassword()));
        user.setRole(newRole);
        userService.saveOrUpdate(user);
    }
}
