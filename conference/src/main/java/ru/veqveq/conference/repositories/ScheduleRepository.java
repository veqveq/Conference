package ru.veqveq.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.User;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduleItem, Long> {
    List<ScheduleItem> findAll();

    List<ScheduleItem> findAllByTalk_Speakers(User speaker);
}
