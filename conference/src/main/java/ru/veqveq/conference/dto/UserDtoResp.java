package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.veqveq.conference.models.User;

@Data
@NoArgsConstructor
public class UserDtoResp {
    private Long id;
    private String login;
    private RoleDto roleDto;
    private UserInfoDto userInfo;

    public UserDtoResp(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.roleDto = new RoleDto(user.getRole());
        if (user.getUserInfo() != null) this.userInfo = new UserInfoDto(user.getUserInfo());
    }
}
