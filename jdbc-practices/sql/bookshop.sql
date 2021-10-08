desc book;
desc author;

-- insert author
insert into author values(null, '스테파니 메이어');
select * from author;


delete from author;
ALTER TABLE author AUTO_INCREMENT = 1;

-- insert book
insert into book values(null, '트와일라잇', 1, '대여가능');
select * from book;
delete from book;
ALTER TABLE book AUTO_INCREMENT = 1;

-- select book
select * 
from author t1, book t2
where t1.no = t2.author_no
order by t1.no asc
;

-- update book's status(no, status)
update book
set status = '대여중'
where no = 1
;