-- Basic Query

DROP TABLE [et;

CREATE TABLE pet
(
		NAME		VARCHAR(20),
        OWNER 		VARCHAR(20),
        SPECIES	VARCHAR(20),
        GENDER		CHAR(1),
        BIRTH		DATE,
        DEATH		DATE
);

DESC pet;

-- 데이터 조회
select name, owner, species, gender, birth, death from pet;

-- 데이터 넣기
insert into pet values ('성탄이', '홍창목', 'dog', 'm', '2021-09-30', null);

-- 데이터 삭제
delete from pet where name = '성탄이';

-- load data local infile
load data local infile "c:\\pet.txt" into table pet;

-- update death
update pet 
set death = null
where name != 'Bowser'
;

-- 조회연습 1: where

-- 1990년 이후에 태어난 아이들은?
select name, species, birth from pet
where birth > '1990-00-00';

-- Gwen과 함께 사는 아이들은?
select name, species, owner from pet
where owner = 'Gwen';

-- null 다루기 1: 살아있는 애들은?
select name, birth, death
from pet
where death is null;

-- null 다루기 2: 죽은 애들은?
select name, birth, death
from pet
where death is not null
;

-- like 검증(패턴 매칭): 이름이 b로 시작하는 아이들은?
select name from pet where name like 'b%';

-- like 검증(패턴 매칭): 이름이 b로 시작하는 아이들 중에 이름이 6자인 아이는?
select name from pet where name like 'b_____';

-- 집계(통계) 함수
select count(*) from pet;
select count(death) from pet;
select count(*) from pet where death is not null;
select





