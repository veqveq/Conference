package ru.veqveq.conference.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "talks_tbl")
@Data
@NoArgsConstructor
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @Column(name = "text_fld")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id_fld")
    private User owner;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "talks_speakers_tbl",
            joinColumns = @JoinColumn(name = "talk_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id_fld"))
    private List<User> speakers;

    public static class Builder {
        private String text;
        private User owner;
        private List<User> speakers;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder setSpeakers(List<User> speakers) {
            this.speakers = speakers;
            return this;
        }

        public Talk build() {
            return new Talk(this);
        }
    }

    public Talk(Builder builder) {
        this.text = builder.text;
        this.owner = builder.owner;
        this.speakers = builder.speakers;
    }
}
