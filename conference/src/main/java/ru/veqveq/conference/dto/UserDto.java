package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.models.UserInfo;

@Data
public class UserDto {
    private Long id;
    private String login;
    private UserInfoDto userInfo;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        if (user.getUserInfo() != null) this.userInfo = new UserInfoDto(user.getUserInfo());
    }
}
