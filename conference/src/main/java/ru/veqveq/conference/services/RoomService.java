package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.models.Room;
import ru.veqveq.conference.repositories.RoomRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Optional<Room> findByNumber(String number) {
        return roomRepository.findByNumber(number);
    }
}
