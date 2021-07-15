package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoReq {
    private Long id;
    private String login;
    private String password;
    private Byte roleId;
}
