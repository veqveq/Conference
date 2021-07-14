package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.veqveq.conference.models.Room;
import ru.veqveq.conference.repositories.RoomRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Optional<Room> findByNumber(String number) {
        return roomRepository.findByNumber(number);
    }

    public List<String> findNumbersList() {
        return roomRepository.findAll()
                .stream()
                .map(Room::getNumber)
                .collect(Collectors.toList());
    }
}
