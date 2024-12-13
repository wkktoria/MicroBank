create table if not exists `loan` (
    `loan_id` int not null auto_increment primary key,
    `mobile_number` varchar(15) not null,
    `loan_number` varchar(100) not null,
    `loan_type` varchar(100) not null,
    `total_loan` double not null,
    `amount_paid` double not null,
    `outstanding_amount` double not null,
    `created_at` date not null,
    `created_by` varchar(20) not null,
    `updated_at` date default null,
    `updated_by` varchar(20) default null
);