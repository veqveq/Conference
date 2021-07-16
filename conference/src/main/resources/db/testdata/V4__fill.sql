insert into roles_tbl(role_fld)
values ('ROLE_ADMIN'),
       ('ROLE_SPEAKER'),
       ('ROLE_LISTENER');

insert into users_tbl(login_fld, password_fld, role_id_fld)
values        ('u2', '$2y$12$OVuHWBHzBtmcHsyeslCMeOkXLERbBDPg8rSML36rTWJXuPbN5/XXq', 2), //200
       ('u4', '$2y$12$jXyB.gesY2X6qc46XsI0K.acYce8lWGJ9K7azCACLpwk5x9tKlmjq', 3), //400

insert into rooms_tbl(number_fld)
values ('11a'),
       ('212b'),
       ('14'),
       ('16/2'),
       ('2');

insert into talks_tbl(text_fld, owner_id_fld)
values ('Talk 1', 2),
       ('Talk 2', 2),
       ('Talk 3', 2),
       ('Talk 4', 3),
       ('Talk 5', 3);

insert into talks_speakers_tbl(talk_id_fld, speaker_id_fld)
values (1, 2),
       (2, 3),
       (3, 2),
       (4, 3),
       (5, 3),
       (5, 2);

insert into schedule_tbl(room_id_fld, talk_id_fld, start_time_fld, end_time_fld)
values (1, 1, '2021-07-10 12:00:00', '2021-07-10 15:00:00'),
       (2, 2, '2021-07-14 12:00:00', '2021-07-10 15:00:00'),
       (3, 3, '2021-07-14 12:00:00', '2021-07-10 15:00:00'),
       (4, 4, '2021-07-16 12:00:00', '2021-07-10 15:00:00'),
       (1, 5, '2021-07-10 16:00:00', '2021-07-10 18:00:00');

insert into schedule_listeners_tbl(schedule_id_fld, listener_id_fld)
values (1, 4),
       (2, 4),
       (3, 5),
       (4, 3),
       (5, 2);


