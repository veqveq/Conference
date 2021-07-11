package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;
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
    @ManyToMany
    @JoinTable(name = "talks_speakers_tbl",
            joinColumns = @JoinColumn(name = "talk_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id_fld"))
    private List<User> speakers;
}
