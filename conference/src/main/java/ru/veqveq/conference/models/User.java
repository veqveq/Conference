package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_tbl")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @Column(name = "login_fld")
    private String login;
    @Column(name = "password_fld")
    private String password;
    @OneToOne
    @JoinColumn(name = "role_id_fld")
    private Role role;
    @OneToOne(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private UserInfo userInfo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "schedule_listeners_tbl",
            joinColumns = @JoinColumn(name = "listener_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id_fld"))
    private List<ScheduleItem> talksAsListener;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "talks_speakers_tbl",
            joinColumns = @JoinColumn(name = "speaker_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "talk_id_fld"))
    private List<Talk> talksAsSpeaker;
}
