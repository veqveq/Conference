package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.Room;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoomDto {
    private Short id;
    private String number;
    private List<ScheduleItemResp> scheduleItems;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.scheduleItems = room.getScheduleItemList().stream().map(ScheduleItemResp::new).collect(Collectors.toList());
    }
}
