package ru.veqveq.conference.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "schedule_tbl")
@Data
@NoArgsConstructor
public class ScheduleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "room_id_fld")
    private Room room;
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "talk_id_fld")
    private Talk talk;
    @Column(name = "start_time_fld")
    private LocalDateTime startTime;
    @Column(name = "end_time_fld")
    private LocalDateTime endTime;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "schedule_listeners_tbl",
            joinColumns = @JoinColumn(name = "schedule_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "listener_id_fld"))
    private List<User> listeners;

    public void addListener(User user) {
        listeners.add(user);
    }

    public void removeListener(User user) {
        if (!listeners.contains(user)) throw new ResourceNotFoundException("User not subscribed in this task");
        for (User u : listeners) {
            if (u.getId().equals(user.getId())) {
                listeners.remove(u);
                break;
            }
        }
    }

    public static class Builder {
        private Room room;
        private Talk talk;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder setTalk(Talk talk) {
            this.talk = talk;
            return this;
        }

        public Builder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder setTimeInterval(TimeInterval timeInterval) {
            setStartTime(timeInterval.startTime).setEndTime(timeInterval.endTime);
            return this;
        }

        public ScheduleItem build() {
            return new ScheduleItem(this);
        }
    }

    public ScheduleItem(Builder builder) {
        this.room = builder.room;
        this.talk = builder.talk;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }
}
