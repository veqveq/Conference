package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.veqveq.conference.models.Talk;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TalkDto {
    private Long id;
    private String text;
    private String owner;
    private List<UserDtoResp> speakers;

    public TalkDto(Talk talk) {
        this.id = talk.getId();
        this.text = talk.getText();
        this.owner = talk.getOwner().getLogin();
        this.speakers = talk.getSpeakers().stream().map(UserDtoResp::new).collect(Collectors.toList());
    }
}
