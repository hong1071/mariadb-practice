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
    
-- 예제 3
-- 현재 회사 상황을 반영한 직원별 근부 부서를 사번, 이름, 근무 부서로 출력해보자.
select t1.emp_no, concat(first_name, ' ', last_name) 'name', t3.dept_name
from employees t1, dept_emp t2, departments t3
where t1.emp_no = t2.emp_no
	and t2.dept_no = t3.dept_no
	and t2.to_date = '9999-01-01'
;

-- 예제 4
-- 현재 회사에서 지급되고 있는 급여체계를 반영한 결과를 출력해보자.
-- 사번, 이흠, 연봉 형태로 출력
select t1.emp_no, concat(first_name, ' ', last_name) 'name', t2.salary
from employees t1, salaries t2
where t1.emp_no = t2.emp_no
	and t2.to_date = '9999-01-01'
order by t2.salary desc;

-- 예제 5: 현재 직책별로 평균 연봉과 인원수를 구하되 직책별로 인원이 100명 이상인 직책만 출력해보자.
select t1.title, avg(salary), count(*)
from titles t1, salaries t2
where t1.emp_no = t2.emp_no
	and t1.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
group by t1.title
having count(*) > 100
order by avg(salary) desc
;


-- 예제 6: 현재 부서별로 현재 직책이 Enginerr인 직원들에 대해서만 평균 급여를 구하세요.
select t4.dept_name, avg(salary)
from dept_emp t1, salaries t2, titles t3, departments t4
where t1.emp_no = t2.emp_no
	and t2.emp_no = t3.emp_no
    and t1.dept_no = t4.dept_no
	and t1.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'
    and t3.title = 'Engineer'
group by t4.dept_name
;

-- 예제 7: 현재 직책별로 급여의 총합을 구하되 Engineer직책은 제외하세요. 
-- 단, 총합이 2,000,000,000이상인 직책만 나타내며 급여총합에 대해서 내림차순(DESC)로 정렬하세요.   
select t2.title, sum(t1.salary)
from salaries t1, titles t2
where t1.emp_no = t2.emp_no
	and t1.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
    and t2.title != 'Engineer'
group by t2.title
having sum(t1.salary) >= 2000000000
order by sum(t1.salary) desc
;





