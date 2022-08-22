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

create table worker
(
    id    bigint  not null AUTO_INCREMENT,
    level varchar(255),
    name  varchar(255),
    point integer not null,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table point_history
(
    id        bigint  not null AUTO_INCREMENT,
    date      date,
    point     integer not null,
    reason    varchar(255),
    worker_id bigint,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create index uix_sku_code
    on sku (code);

create index uix_location_code
    on location (code);

create index uix_worker_name
    on worker (name);

