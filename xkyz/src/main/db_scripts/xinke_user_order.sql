create table user_order
(
    order_id        int auto_increment
        primary key,
    order_no        varchar(100)   null,
    stuff_type      varchar(100)   null,
    send_address    int            not null,
    receive_address int            not null,
    status          int            not null,
    order_time      varchar(100)   null,
    open_id         varchar(40)    null,
    price           decimal(10, 2) null
);

INSERT INTO xinke.user_order (order_id, order_no, stuff_type, send_address, receive_address, status, order_time, open_id, price) VALUES (2, 'dfgfdg', '衣服', 1, 5, 1, '2022-1-12 08:00:00', 'oVoNf5GAzZ3WULwRYyj4yppG4y6U', 543.55);
INSERT INTO xinke.user_order (order_id, order_no, stuff_type, send_address, receive_address, status, order_time, open_id, price) VALUES (3, 'dfgfdg', '衣服', 1, 5, 1, '2022-1-12 08:00:00', 'oVoNf5GAzZ3WULwRYyj4yppG4y6U', 543.55);
