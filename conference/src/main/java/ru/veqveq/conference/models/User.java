package ru.veqveq.conference.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;

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
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(name = "users_roles_tbl",
            joinColumns = @JoinColumn(name = "user_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "role_id_fld"))
    private List<Role> roles;
    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(name = "schedules_listeners_tbl",
            joinColumns = @JoinColumn(name = "listener_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id_fld"))
    private List<ScheduleItem> talksAsListener;
    @ManyToMany
    @JoinTable(name = "talks_speakers_tbl",
            joinColumns = @JoinColumn(name = "speaker_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "talk_id_fld"))
    private List<Talk> talksAsSpeaker;
}
