package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.Role;

@Data
public class RoleDto {
    private Byte id;
    private String role;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
    }
}
