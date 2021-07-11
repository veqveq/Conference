package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.ScheduleItem;

import java.time.LocalDateTime;

@Data
public class ScheduleItemDto {
    private Long id;
    private String room;
    private TalkDto talkDto;
    private int listenersCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScheduleItemDto(ScheduleItem scheduleItem) {
        this.id = scheduleItem.getId();
        this.room = scheduleItem.getRoom().getNumber();
        this.talkDto = new TalkDto(scheduleItem.getTalk());
        this.listenersCount = scheduleItem.getListeners().size();
        this.startTime = scheduleItem.getStartTime();
        this.endTime = scheduleItem.getEndTime();
    }
}
