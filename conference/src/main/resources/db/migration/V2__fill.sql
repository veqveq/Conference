insert into users_tbl(login_fld, password_fld)
values ('u1', '$2y$12$fdgMU9AMXKxZ9Jur1eaO5O2gs9xfifsV7Wh52rJ4wjf.Acv4c8WuS'), //100
       ('u2', '$2y$12$OVuHWBHzBtmcHsyeslCMeOkXLERbBDPg8rSML36rTWJXuPbN5/XXq '), //200
       ('u3', '$2y$12$u.hqdbXNO/d8YDGiuNuNLuV8HyA3fHT6dgeCGj5j8cVhdAr/NRXpC '), //300
       ('u4', '$2y$12$VKRcXG1mRDAILwVpAwQtOuUaS4jMGZeTvJg8Lp4.OdzspdbQfvSDy '), //400
       ('u5', '$2y$12$/uml2Bkb/FO78dMF5e0HI.ckIKi1ceqTaYMSweW2xPMXOF6nqicxa '); //500

insert into user_info_tbl(user_id_fld, first_name_fld, last_name_fld, age_fld, phone_fld)
values (1, 'Name1', 'Surname1', '20', '+7(800)555-35-35');

insert into user_info_tbl(user_id_fld, first_name_fld, last_name_fld, age_fld)
values (2, 'Name2', 'Surname2', '30');

insert into user_info_tbl(user_id_fld, first_name_fld, age_fld)
values (4, 'Name3', '45');

insert into roles_tbl(role_fld)
values ('ROLE_ADMIN'),
       ('ROLE_SPEAKER'),
       ('ROLE_LISTENER');

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


