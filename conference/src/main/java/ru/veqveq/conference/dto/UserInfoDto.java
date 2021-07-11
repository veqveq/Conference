package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.UserInfo;

@Data
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private Byte age;
    private String phone;

    public UserInfoDto(UserInfo userInfo) {
        if (userInfo.getFirstName() != null) this.firstName = userInfo.getFirstName();
        if (userInfo.getLastName() != null) this.lastName = userInfo.getLastName();
        if (userInfo.getAge() != null) this.age = userInfo.getAge();
        if (userInfo.getPhone() != null) this.phone = userInfo.getPhone();
    }
}
