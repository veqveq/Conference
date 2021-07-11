package ru.veqveq.conference.services;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.UserDto;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id" + id + "not found")
                );
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
