package ru.veqveq.conference.services;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.repositories.TalkRepository;

@Service
@RequiredArgsConstructor
public class TalkService {
    private final TalkRepository talkRepository;

    public Talk findById(Long id) throws NotFoundException {
        return talkRepository.findById(id).orElseThrow(() -> new NotFoundException("Talk by id: " + id + " not found"));
    }
}
