create table location
(
    location_id bigint       not null AUTO_INCREMENT,
    code        varchar(255) not null,
    primary key (location_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table sku
(
    sku_id  bigint       not null AUTO_INCREMENT,
    barcode varchar(20)  not null,
    code    varchar(255) not null,
    name    varchar(20)  not null,
    status  varchar(255) not null,
    primary key (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


create table stock
(
    stock_id    bigint  not null AUTO_INCREMENT,
    quantity    integer not null,
    location_id bigint  not null,
    sku_id      bigint  not null,
    primary key (stock_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
