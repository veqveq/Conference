package ru.veqveq.conference.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles_tbl")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Byte id;
    @Column(name = "role_fld")
    private String role;
}
