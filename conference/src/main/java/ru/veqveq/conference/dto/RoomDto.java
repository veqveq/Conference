package ru.veqveq.conference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.veqveq.conference.models.Room;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
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
