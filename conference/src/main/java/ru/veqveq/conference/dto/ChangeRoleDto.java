package ru.veqveq.conference.dto;

import lombok.Data;

@Data
public class ChangeRoleDto {
    private Long userId;
    private Byte roleId;
}
