package ru.veqveq.conference.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.conference.dto.ScheduleItemResp;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.exceptions.SubscribeException;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.services.MailService;
import ru.veqveq.conference.services.ScheduleService;
import ru.veqveq.conference.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersSchedulesFacade {
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final MailService mailService;

    @Transactional
    public List<ScheduleItemResp> getSpeaks(Principal principal) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User by name: %s not exist", principal.getName())));
        return scheduleService.findAllBySpeaker(user)
                .stream()
                .map(ScheduleItemResp::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ScheduleItemResp> getTalks(Principal principal) {
        User user = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User by name: %s not exist", principal.getName())));
        return user.getTalksAsListener()
                .stream()
                .map(ScheduleItemResp::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void subscribeAsListener(Principal principal, Long scheduleId) {
        String name = principal.getName();
        User user = userService.findByLogin(name)
                .orElseThrow(() -> new ResourceNotFoundException("User by username: " + name + " not exist"));
        ScheduleItem talk = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Talk by id: " + scheduleId + " not exist"));
        if (talk.getListeners().contains(user)) throw new SubscribeException("You are already subscribed this talk");
        talk.addListener(user);
        mailService.greatMessage(user,talk);
    }

    @Transactional
    public void unsubscribeAsListener(Principal principal, Long scheduleId) {
        String name = principal.getName();
        User user = userService.findByLogin(name)
                .orElseThrow(() -> new ResourceNotFoundException("User by username: " + name + " not exist"));
        ScheduleItem talk = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Talk by id: " + scheduleId + " not exist"));
        talk.removeListener(user);
    }

    @Transactional
    public void cleanSpeakerWithSchedule(User speaker) {
        List<ScheduleItem> scheduleItems = scheduleService.findAllBySpeaker(speaker);
        scheduleItems.forEach(scheduleItem -> {
            scheduleItem.getTalk().getSpeakers().remove(speaker);
        });

        List<ScheduleItem> deletedSchedules = new ArrayList<>();
        for (ScheduleItem si : scheduleItems) {
            if (si.getTalk().getSpeakers().size() == 0 || si.getTalk().getOwner().equals(speaker)) {
                deletedSchedules.add(si);
            }
        }
        scheduleItems.removeIf(scheduleItem -> scheduleItem.getTalk().getSpeakers().size() == 0);

        scheduleService.save(scheduleItems);
        scheduleService.removeAll(deletedSchedules);
    }

    @Transactional
    public void cleanSpeakerWithSchedule(Long speakerId) {
        User speaker = userService.findById(speakerId)
                .orElseThrow(() -> new ResourceNotFoundException("User by id : " + speakerId + " not exist"));
        cleanSpeakerWithSchedule(speaker);
    }
}
