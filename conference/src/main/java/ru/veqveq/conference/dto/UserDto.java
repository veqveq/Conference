package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.veqveq.conference.models.User;

@Data
@NoArgsConstructor
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
