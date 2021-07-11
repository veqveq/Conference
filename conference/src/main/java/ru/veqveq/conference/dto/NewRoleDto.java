package ru.veqveq.conference.dto;

import lombok.Data;

@Data
public class NewRoleDto {
    private Long userId;
    private Byte roleId;
}
