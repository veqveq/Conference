package ru.veqveq.conference.facades;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.TalkDto;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.Talk;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.ScheduleRepository;
import ru.veqveq.conference.services.ScheduleService;
import ru.veqveq.conference.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersSchedulesFacade {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    public List<TalkDto> getTalks(Principal principal) {
//        String name = principal.getName();
        String name = "u2";
        User user = userService.findByLogin(name);
        List<ScheduleItem> scheduleItem = scheduleRepository.findAllByTalk_Speakers(user);
        List<Talk> talks = scheduleItem.stream().map(ScheduleItem::getTalk).collect(Collectors.toList());
        return talks.stream().map(TalkDto::new).collect(Collectors.toList());
    }
}
