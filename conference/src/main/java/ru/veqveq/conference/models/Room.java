package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms_tbl")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Short id;
    @Column(name = "number_fld")
    private String number;
    @ManyToMany
    @JoinTable(name = "schedule_tbl",
            joinColumns = @JoinColumn(name = "room_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "talk_id_fld"))
    private List<Talk> talks;
}
