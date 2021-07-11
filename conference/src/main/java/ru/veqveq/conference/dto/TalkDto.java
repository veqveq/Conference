package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.models.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TalkDto {
    private Long id;
    private String text;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<UserDto> speakers;
    private List<UserDto> listeners;

    public TalkDto(Talk talk) {
        this.id = talk.getId();
        this.text = talk.getText();
        this.startTime = talk.getStartTime();
        this.endTime = talk.getEndTime();
        this.speakers = talk.getSpeakers().stream().map(UserDto::new).collect(Collectors.toList());
        this.listeners = talk.getListeners().stream().map(UserDto::new).collect(Collectors.toList());
    }
}
