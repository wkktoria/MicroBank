create table if not exists `card` (
    `card_id` int not null auto_increment primary key,
    `mobile_number` varchar(15) not null,
    `card_number` varchar(100) not null,
    `card_type` varchar(100) not null,
    `total_limit` double not null,
    `amount_used` double not null,
    `available_amount` double not null,
    `created_at` date not null,
    `created_by` varchar(20) not null,
    `updated_at` date default null,
    `updated_by` varchar(20) default null
);