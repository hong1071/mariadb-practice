-- inner join
-- 예제 1: employees 테이블과 titles 테이블을 join하여 직원의 이름과 직책을 출력하자
select concat(first_name, ' ', last_name) as name, title
from employees t1, titles t2
where t1.emp_no = t2.emp_no				-- 조인 조건
	and t2.to_date = '9999-01-01'		-- row 선택 조건
;

-- 예제 2: employees 테이블과 titles 테이블을 join하여 직원의 이름과 직책을 출력하되 여성 엔지니어만 출력하자
select concat(first_name, ' ', last_name) as name, title
from employees t1, titles t2
where t1.emp_no = t2.emp_no				-- 조인 조건
	and t2.to_date = '9999-01-01'		-- row 선택 조건 1
	and t1.gender = 'F'					-- row 선택 조건 2
    and t2.title = 'engineer'			-- row 선택 조건 3
;

-- ANSI?ISO SQL1999 JOIN 표준 문법
-- 1) natural join: 두 테이블에 이름이 공통인 컬럼이 있으면 별다른 조인 조건 없이 암묵적으로 조인이 됨.
-- 잘 쓰이지 않음.
	select concat(first_name, ' ', last_name) as name, title
	from employees t1 natural join titles t2 /* on t1.emp_no = t2.emp_no */ -- 조인 조건이 생략됨
	where t2.to_date = '9999-01-01'		-- row 선택 조건
	;
    
-- 2) join ~ using: join에 사용할 공통 컬럼을 using 뒤에 지정
    select concat(first_name, ' ', last_name) as name, title
	from employees t1 join titles t2 
		using (emp_no)
	where t2.to_date = '9999-01-01'		-- row 선택 조건
	;
    
-- 3) join ~ on:
	select concat(first_name, ' ', last_name) as name, title
	from employees t1 join titles t2 
		on t1.emp_no = t2.emp_no
	where t2.to_date = '9999-01-01'		-- row 선택 조건
	;
    
-- outer join
	/*
	insert into dept values(null, '총무');
	insert into dept values(null, '영업');
	insert into dept values(null, '개발');
	insert into dept values(null, '기획');
	*/
	select * from dept;
	/*
	insert into emp values(null, '둘리', 1);
	insert into emp values(null, '마이콜', 2);
	insert into emp values(null, '또치', 3);
	insert into emp values(null, '길동이', null);
	*/
	select * from emp;

	select t1.name as '이름', ifnull(t2.name, '없음') as '부서'
	from emp t1 left join dept t2 
		on t1.dept_no = t2.no
	;

	select t1.name as '이름', t2.name as '부서'
	from emp t1 right join dept t2 
		on t1.dept_no = t2.no
	;
