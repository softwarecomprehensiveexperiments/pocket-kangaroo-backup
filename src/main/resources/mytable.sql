-- auto-generated definition
create database kangaroo;
use kangaroo;
create table users
(
    user_id         int         not null auto_increment,
    user_phone      varchar(11) not null,
    user_name       varchar(16) not null,
    user_password   varchar(16) not null,
    user_sex        int         not null,
    user_icon       varchar(64) null,
    user_properties int         null,
    user_lastIp     varchar(16) null,
    user_lastDate   date        null,
    primary key (user_id),
    unique (user_name)
)engine=InnoDB auto_increment=1 default charset=utf8;

create table task
(
    task_id       int          not null auto_increment,
    task_title    varchar(32)  not null,
    task_deadLineDate datetime     not null,
    task_content  text         null,
    task_price        int          not null,
    task_publisherId  int          not null,
    task_receiversId  varchar(128) null,
    task_publishDate  datetime     not null,
    task_type     varchar(32)  null,
    task_completeDate date         not null,
    task_state        int          null,
    primary key (task_id)
)engine=InnoDB auto_increment=1 default charset=utf8;

create table loginlog
(
    loginLog_id int          not null auto_increment,
    loginLog_userId     int          not null,
    loginLog_ip         varchar(128) not null,
    loginLog_date       date         not null,
    primary key (loginLog_id),
    unique (loginLog_userId)
)engine=InnoDB auto_increment=1 default charset=utf8;