package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "talks_tbl")
@Data
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @Column(name = "text_fld")
    private String text;
    @Column(name = "start_time_fld")
    private LocalDateTime startTime;
    @Column(name = "end_time_fld")
    private LocalDateTime endTime;
    @ManyToMany
    @JoinTable(name = "talks_speakers_tbl",
            joinColumns = @JoinColumn(name = "talk_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id_fld"))
    private List<User> speakers;
    @ManyToMany
    @JoinTable(name = "talks_listeners_tbl",
            joinColumns = @JoinColumn(name = "talk_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "listener_id_fld"))
    private List<User> listeners;

    public void addListener(User user) {
        listeners.add(user);
    }

    public void removeListener(User user) {
        listeners.remove(user);
    }
}
