package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_info_tbl")
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id_fld")
    private User user;
    @Column(name = "first_name_fld")
    private String firstName;
    @Column(name = "last_name_fld")
    private String lastName;
    @Column(name = "age_fld")
    private Byte age;
    @Column(name = "phone_fld")
    private String phone;

}
