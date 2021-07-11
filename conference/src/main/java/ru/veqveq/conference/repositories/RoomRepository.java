package ru.veqveq.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.veqveq.conference.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Short> {
}
