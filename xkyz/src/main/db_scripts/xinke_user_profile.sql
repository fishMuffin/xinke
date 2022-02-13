create table user_profile
(
    id           int auto_increment
        primary key,
    phone_number int          null,
    wx_name      varchar(100) null,
    password     varchar(40)  not null,
    portrait_url varchar(100) null
);

