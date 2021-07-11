package ru.veqveq.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.veqveq.conference.models.Room;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Short> {
}
