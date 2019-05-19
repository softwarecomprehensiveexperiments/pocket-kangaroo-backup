-- auto-generated definition
create table users
(
    id         int         not null,
    phone      varchar(11) null,
    name       varchar(16) not null,
    password   varchar(16) not null,
    sex        int         not null,
    icon       varchar(64) null,
    properties int         null,
    lastIP     varchar(16) null,
    lastDate   date        null,
    constraint users_id_uindex
        unique (id),
    constraint users_name_uindex
        unique (name),
    constraint users_password_uindex
        unique (password),
    constraint users_phone_uindex
        unique (phone)
);

alter table users
    add primary key (id);

create table task
(
    taskId       int          not null,
    taskTitle    varchar(32)  null,
    deadLineDate datetime     not null,
    taskContent  text         not null,
    price        int          not null,
    publisherId  int          not null,
    receiversId  varchar(128) null,
    publishDate  datetime     not null,
    taskType     varchar(32)  null,
    completeDate date         not null,
    state        int          null,
    constraint task_taskId_uindex
        unique (taskId)
);

alter table task
    add primary key (taskId);

create table loginlog
(
    loginLogId int          not null,
    userId     int          not null,
    ip         varchar(128) not null,
    date       date         not null,
    constraint LoginLog_loginLogId_uindex
        unique (loginLogId),
    constraint LoginLog_userId_uindex
        unique (userId)
);

alter table loginlog
    add primary key (loginLogId);