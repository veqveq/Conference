package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.Room;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoomDto {
    private Short id;
    private String number;
    private List<ScheduleItemDto> talks;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.talks = room.getScheduleItemList().stream().map(ScheduleItemDto::new).collect(Collectors.toList());
    }
}
