package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "start_time_fld")
    private LocalDateTime startTime;
    @Column(name = "end_time_fld")
    private LocalDateTime endTime;
    @ManyToMany
    @JoinTable(name = "schedule_listeners_tbl",
            joinColumns = @JoinColumn(name = "schedule_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "listener_id_fld"))
    private List<User> listeners;

    public void addListener(User user) {
        listeners.add(user);
    }

    public void removeListener(User user) {
        for (User u : listeners) {
            if (u.getId().equals(user.getId())) {
                listeners.remove(u);
                break;
            }
        }
    }

    public void setTimeInterval(TimeInterval timeInterval) {
        setStartTime(timeInterval.startTime);
        setEndTime(timeInterval.endTime);
    }
}
