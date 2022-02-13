create table user_address
(
    address_id     int auto_increment
        primary key,
    province       varchar(100) null,
    city           varchar(100) null,
    district       varchar(100) null,
    address_detail varchar(200) null,
    user_id        int          null
);

INSERT INTO xinke.user_address (address_id, province, city, district, address_detail, user_id) VALUES (1, '安徽省', '合肥市', '包河区', '四十八中', 1);
INSERT INTO xinke.user_address (address_id, province, city, district, address_detail, user_id) VALUES (5, '湖北省', '武汉市', 'ccc', 'bbb', 1);
INSERT INTO xinke.user_address (address_id, province, city, district, address_detail, user_id) VALUES (7, '浙江省', '杭州市', '拱墅区', '小河路', 2);
