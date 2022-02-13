create table user
(
    id          int auto_increment
        primary key,
    open_id     varchar(40)  null,
    session_key varchar(40)  null,
    role        int          null,
    skey        varchar(100) null
);

INSERT INTO xinke.user (id, open_id, session_key, role, skey) VALUES (1, 'oVoNf5GAzZ3WULwRYyj4yppG4y6U', '1cc8QkHLA5461m3Bh0BtQQ==', null, '16d45d02-64cb-4134-ad3c-837cf695ba83');
