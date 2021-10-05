create table member
(
	no 			int(11) 		not null		auto_increment,
    email		varchar(200)	not null,
    password	varchar(64)		not null,
    name		varchar(100)	not null,
    department	varchar(100),
    primary key(no)
);
desc member;
alter table member add juminbunho char(13) not null after email;
desc member;
alter table member drop juminbunho;
desc member;
alter table member add join_date datetime not null;
desc member;
alter table member change deparment departments varchar(100) not null;
desc member;