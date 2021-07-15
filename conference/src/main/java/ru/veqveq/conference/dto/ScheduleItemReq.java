package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleItemReq {

    private Long id;
    private String room;
    private String startTime;
    private String endTime;
    private Long talkId;
    private String text;
    private List<Long> speakers;

    public LocalDateTime getStartTime() {
        return LocalDateTime.parse(startTime);
    }

    public LocalDateTime getEndTime() {
        return LocalDateTime.parse(endTime);
    }
}
