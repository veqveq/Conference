package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.repositories.TalkRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TalkService {
    private final TalkRepository talkRepository;

    public Optional<Talk> findById(Long id) {
        return talkRepository.findById(id);
    }

    public void save(Talk talk) {
        talkRepository.save(talk);
    }

    public void remove(Long talkId) {
        talkRepository.deleteById(talkId);
    }
}
