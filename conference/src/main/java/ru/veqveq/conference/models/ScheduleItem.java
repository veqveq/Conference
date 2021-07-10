package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedule_tbl")
@Data
public class ScheduleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "room_id_fld")
    private Room room;
    @OneToOne
    @JoinColumn(name = "talk_id_fld")
    private Talk talk;
    @ManyToMany
    @JoinTable(name = "schedules_listeners_tbl",
            joinColumns = @JoinColumn(name = "schedule_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "listener_id_fld"))
    private List<User> listeners;
}
