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
alter table member add self_intro text;
desc member;
alter table member change deparment departments varchar(100) not null;
desc member;

-- select
select * from member;

-- insert
insert into member(no, email, password, name, department, join_date, self_intro)
values(null, 'hong1071@nate.com', password('1234'), '홍창목', '개발팀', now(), null);

insert into member(no, email, password, name, department, join_date, self_intro)
values(null, 'hong1071@nate.com', password('1234'), '홍지연', '개발팀', now(), null);

insert into member(no, email, password, name, department, join_date, self_intro)
values(null, 'hong1071@nate.com', password('1234'), '홍지혜', '개발팀', now(), null);

-- update
update member
set email = 'hongchangmok@naver.com'
where no = 1
;

-- delete
delete from member
where no = 3
;

-- transaction
select @@autocommit;
set autocommit=0;

insert into member(no, email, password, name, department, join_date, self_intro)
values(null, 'hong1071@nate.com', password('1234'), '홍창목', '개발팀', now(), null);

