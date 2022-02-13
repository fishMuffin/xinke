create table deliver_task
(
    task_id         int auto_increment
        primary key,
    deliver_open_id varchar(40)    null,
    express_amount  int            not null,
    store_phone     varchar(40)    null,
    store_address   varchar(100)   null,
    store_name      varchar(40)    null,
    express_company varchar(40)    null,
    owner_open_id   varchar(40)    null,
    order_no        varchar(100)   null,
    price           decimal(10, 2) null,
    weight          int            not null,
    image_url       varchar(100)   null,
    status          int            not null
);

