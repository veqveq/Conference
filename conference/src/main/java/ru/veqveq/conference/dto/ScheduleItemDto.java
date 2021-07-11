package ru.veqveq.conference.dto;

import lombok.Data;
import ru.veqveq.conference.models.ScheduleItem;
import java.util.Objects;

@Data
public class ScheduleItemDto {
    private Long id;
    private RoomDto roomDto;

    public ScheduleItemDto(ScheduleItem scheduleItem) {
        this.id = scheduleItem.getId();
        this.roomDto = new RoomDto(scheduleItem.getRoom());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleItemDto that = (ScheduleItemDto) o;
        return Objects.equals(roomDto, that.roomDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomDto);
    }
}
