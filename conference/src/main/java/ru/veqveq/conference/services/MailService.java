package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.models.ScheduleItem;
import ru.veqveq.conference.models.User;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailSender mailSender;

    @Async("threadPoolTaskExecutor")
    public void sendMessage(User user, String subject, String message) {
        if (user.getUserInfo().getEMail() == null) return;
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("WA.test.conf.smtp@gmail.com");
        mail.setTo(user.getUserInfo().getEMail());
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);
    }

    public void greatMessage(User user, ScheduleItem si) {
        String subject = "Регистрация на конферению";
        String text = String.format("Уважаемый, %s!%n Вы зарегистрировались на конференцию %s%n Доклад начнется в %s в аудитории №%s. Примерное время окончания - %s%n"
                , user.getLogin()
                , si.getTalk().getText()
                , si.getStartTime()
                , si.getRoom().getNumber()
                , si.getEndTime()
        );
        sendMessage(user, subject, text);
    }

    @Bean("threadPoolTaskExecutor")
    private TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(500);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
