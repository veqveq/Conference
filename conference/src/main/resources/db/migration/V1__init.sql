create table roles_tbl
(
    id_fld   tinyint      not null auto_increment,
    role_fld varchar(255) not null,
    primary key (id_fld)
);

create table users_tbl
(
    id_fld       bigint       not null auto_increment,
    login_fld    varchar(255) not null unique,
    password_fld varchar(255) not null,
    role_id_fld  tinyint      not null,
    primary key (id_fld),
    foreign key (role_id_fld) references roles_tbl (id_fld)
);

create index users_login_index on users_tbl (login_fld);

create table user_info_tbl
(
    id_fld         bigint not null auto_increment,
    user_id_fld    bigint not null,
    first_name_fld varchar(255),
    last_name_fld  varchar(255),
    age_fld        tinyint,
    phone_fld      varchar(255),
    email_fld      varchar(255),
    primary key (id_fld),
    foreign key (user_id_fld) references users_tbl (id_fld)
);

create table rooms_tbl
(
    id_fld     smallint     not null auto_increment,
    number_fld varchar(255) not null,
    primary key (id_fld)
);

create index rooms_number_index on rooms_tbl (number_fld);

create table talks_tbl
(
    id_fld       bigint  not null auto_increment,
    text_fld     varchar not null,
    owner_id_fld bigint  not null,
    primary key (id_fld),
    foreign key (owner_id_fld) references users_tbl (id_fld)
);

create table talks_speakers_tbl
(
    talk_id_fld    bigint not null,
    speaker_id_fld bigint not null,
    foreign key (talk_id_fld) references talks_tbl (id_fld),
    foreign key (speaker_id_fld) references users_tbl (id_fld)
);

create table schedule_tbl
(
    id_fld         bigint   not null auto_increment,
    room_id_fld    smallint not null,
    talk_id_fld    bigint   not null,
    start_time_fld datetime not null,
    end_time_fld   datetime not null,
    primary key (id_fld),
    foreign key (room_id_fld) references rooms_tbl (id_fld),
    foreign key (talk_id_fld) references talks_tbl (id_fld)
);

create table schedule_listeners_tbl
(
    schedule_id_fld bigint not null,
    listener_id_fld bigint not null,
    foreign key (schedule_id_fld) references schedule_tbl (id_fld),
    foreign key (listener_id_fld) references users_tbl (id_fld)
);