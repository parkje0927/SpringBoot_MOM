drop table if exists member;

create or replace table member
(
	id bigint(11) unsigned auto_increment primary key,
	email varchar(40) null,
	name varchar(50) null,
	picture varchar(255) null,
	role varchar(50) null
);