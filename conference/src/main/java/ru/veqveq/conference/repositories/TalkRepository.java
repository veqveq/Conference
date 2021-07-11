package ru.veqveq.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.veqveq.conference.models.Talk;

import java.util.List;

@Repository
public interface TalkRepository extends CrudRepository<Talk, Long> {
}
