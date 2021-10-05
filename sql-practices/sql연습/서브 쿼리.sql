-- 서브쿼리
-- 1) from절의 서브 쿼리
	select now() as n, sysdate() as b, 3 + 1 as c;

	select *
	from(
		select now() as n, sysdate() as b, 3 + 1 as c
		) s
	;

-- 2) where절의 서브 쿼리
-- 예제 1: 현재 Fai Bale이 근무하는 부서에서 근무하는 직원으 ㅣ사번, 전체 이름을 출력해보자
select t2.dept_no
from employees t1, dept_emp t2
where t1.emp_no = t2.emp_no
	and t2.to_date = '9999-01-01'
	and concat(first_name, ' ', last_name) = 'Fai Bale'
;

select t1.emp_no, t1.first_name
from employees t1, dept_emp t2
where t1.emp_no = t2.emp_no
	and t2.to_date = '9999-01-01'
	and t2.dept_no = (
						select t2.dept_no
						from employees t1, dept_emp t2
						where t1.emp_no = t2.emp_no
							and t2.to_date = '9999-01-01'
							and concat(first_name, ' ', last_name) = 'Fai Bale'
                        )
;

-- 2-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제 1
-- 현재 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 출력하자
select concat(t1.first_name, ' ', t1.last_name) as name, salary
from employees t1, salaries t2
where t1.emp_no = t2.emp_no
	and t2.to_date = '9999-01-01'
	and t2.salary < (
					select avg(salary)
					from salaries
					where to_date = '9999-01-01'
					)
order by t2.salary desc;
;

-- 실습문제 2
-- 현재 가장 적은 평균 급여의 직책과 평균 급여를 출력하자
	-- 1) 직책별 평균 급여
	select title, avg(salary) as avg_salary
	from titles t1, salaries t2
	where t1.emp_no = t2.emp_no
		and t1.to_date = '9999-01-01'
		and t2.to_date = '9999-01-01'
	group by title
	;

	-- 2) 가장 적은 평균 급여
	select title, min(avg_salary)
	from(
		select title, avg(salary) as avg_salary
		from titles t1, salaries t2
		where t1.emp_no = t2.emp_no
			and t1.to_date = '9999-01-01'
			and t2.to_date = '9999-01-01'
		group by title
		) tt1
	;

	-- 3) 가장 적은 평균 급여: sol2
	select title, avg(salary) as avg_salary
	from titles t1, salaries t2
	where t1.emp_no = t2.emp_no
		and t1.to_date = '9999-01-01'
		and t2.to_date = '9999-01-01'
	group by title
	order by avg(salary) asc
	limit 0,1
	;

-- 2-2) 복수행 연산자: in, not in, any all

-- any 사용법
	-- 1. = any
	-- 2. > any, >= any : 최소값
	-- 3. < any, <= any : 최소값
	-- 4. <> any: not in과 동일

	select * 
	from salaries
	where salary in (10000, 20000, 30000)
	;
    
-- all 사용법
	-- 1. = all(x)
    -- 2. > all, >= all: 최대값
    -- 3. < all, <= all: 최소값


-- 실습문제 3: 현재 급여가 50000 이상인 직원의 이름을 출력하자.
	-- sol1: join
	select t1.first_name, t2.salary
	from employees t1, salaries t2
	where t1.emp_no = t2.emp_no
		and t2.to_date = '9999-01-01'
		and salary >= 50000
	;
	
    -- sol2: subquery(in)
    select t1.first_name, b.salary
    from employees t1, salaries t2
    where t1.emp_no = t2.emp_no
		and t2.to_date = '9999-01-01'
        and (t1.emp_no, t2.salary) in (
			  						  select emp_no, salary
									  from salaries 
									  where to_date = '9999-01-01'
									  	  and salary > 50000
									  )
    ;
    
    -- sol3: subquery(=any)
    select t1.first_name, b.salary
    from employees t1, salaries t2
    where t1.emp_no = t2.emp_no
		and t2.to_date = '9999-01-01'
        and (t1.emp_no, t2.salary) in (
			  						  select emp_no, salary
									  from salaries 
									  where to_date = '9999-01-01'
									  	  and salary > 50000
									  )
    ;

-- 실습문제 4: 각 부서별로 최고 월급을 받는 직원의 이름과 월급을 출력하자
select t1.dept_no, max(t2.salary)
from dept_emp t1, salaries t2
where t1.emp_no = t2.emp_no
	and t1.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
group by t1.dept_no
;

-- sol1: where subquery = any(in)
select t3.first_name, t3.hire_date, t4.dept_name, t2.salary
from dept_emp t1, salaries t2, employees t3, departments t4
where t1.emp_no = t3.emp_no
	and t2.emp_no = t3.emp_no
    and t1.dept_no = t4.dept_no
    and t1.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
	and (t1.dept_no, t2.salary) in (
									select t1.dept_no, max(t2.salary)
									from dept_emp t1, salaries t2
									where t1.emp_no = t2.emp_no
										and t1.to_date = '9999-01-01'
										and t2.to_date = '9999-01-01'
									group by t1.dept_no
									)
;

-- sol2: from subquery
select t3.first_name, t3.hire_date, t2.salary
from dept_emp t1, salaries t2, employees t3, (
												select t1.dept_no, max(t2.salary) as max_salary
												from dept_emp t1, salaries t2
												where t1.emp_no = t2.emp_no
													and t1.to_date = '9999-01-01'
													and t2.to_date = '9999-01-01'
												group by t1.dept_no
												) t4
where t1.emp_no = t3.emp_no
	and t2.emp_no = t3.emp_no
    and t1.dept_no = t4.dept_no
    and t1.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
    and t2.salary = t4.max_salary
;




