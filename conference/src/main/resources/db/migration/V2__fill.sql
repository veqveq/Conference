insert into users_tbl(login_fld, password_fld)
values ('u1', '100'),
       ('u2', '200'),
       ('u3', '300'),
       ('u4', '400'),
       ('u5', '500');

insert into user_info_tbl(user_id_fld, first_name_fld, last_name_fld, age_fld, phone_fld)
values (1, 'Name1', 'Surname1', '20', '+7(800)555-35-35');

insert into user_info_tbl(user_id_fld, first_name_fld, last_name_fld, age_fld)
values (2, 'Name2', 'Surname2', '30');

insert into user_info_tbl(user_id_fld, first_name_fld, age_fld)
values (4, 'Name3', '45');

insert into roles_tbl(role_fld)
values ('Admin'),
       ('Speaker'),
       ('Listener');

insert into users_roles_tbl(user_id_fld, role_id_fld)
values (1, 1),
       (2, 2),
       (3, 2),
       (4, 3),
       (5, 3);

insert into rooms_tbl(number_fld)
values ('11a'),
       ('212b'),
       ('14'),
       ('16/2'),
       ('2');

insert into talks_tbl(text_fld, start_time_fld, end_time_fld)
values ('Talk 1', '2021-07-10 12:00:00', '2021-07-10 15:00:00'),
       ('Talk 2', '2021-07-12 12:00:00', '2021-07-10 15:00:00'),
       ('Talk 3', '2021-07-14 12:00:00', '2021-07-10 15:00:00'),
       ('Talk 4', '2021-07-16 12:00:00', '2021-07-10 15:00:00'),
       ('Talk 5', '2021-07-18 16:00:00', '2021-07-10 18:00:00');

insert into talks_speakers_tbl(talk_id_fld, speaker_id_fld)
values (1, 2),
       (2, 2),
       (2, 3),
       (3, 2),
       (4, 3),
       (5, 3),
       (5, 2);

insert into schedule_tbl(room_id_fld, talk_id_fld)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (1, 5);


