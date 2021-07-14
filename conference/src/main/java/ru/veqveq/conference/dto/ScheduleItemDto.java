package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.veqveq.conference.models.ScheduleItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ScheduleItemDto {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long id;
    private String room;
    private TalkDto talkDto;
    private int listenersCount;
    private String startTime;
    private String endTime;

    public ScheduleItemDto(ScheduleItem scheduleItem) {
        this.id = scheduleItem.getId();
        this.room = scheduleItem.getRoom().getNumber();
        this.talkDto = new TalkDto(scheduleItem.getTalk());
        this.listenersCount = scheduleItem.getListeners().size();
        this.startTime = scheduleItem.getStartTime().format(DATE_TIME_FORMATTER);
        this.endTime = scheduleItem.getEndTime().format(DATE_TIME_FORMATTER);
    }

    public LocalDateTime getStartTime() {
        return LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
    }

    public LocalDateTime getEndTime() {
        return LocalDateTime.parse(endTime, DATE_TIME_FORMATTER);
    }
}
