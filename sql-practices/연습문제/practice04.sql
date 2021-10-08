-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries
where to_date = '9999-01-01'
	and salary > (
				select avg(salary)
				from salaries
				where to_date = '9999-01-01'
                )
;

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서, 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.
-- 풀이 1
select t1.emp_no, t1.first_name, t3.dept_name, max(t4.salary) as max_salary
from employees t1, dept_emp t2, departments t3, salaries t4
where t1.emp_no = t2.emp_no
	and t2.dept_no = t3.dept_no
    and t1.emp_no = t4.emp_no
	and t2.to_date = '9999-01-01'
    and t4.to_date = '9999-01-01'
group by t3.dept_name
order by max_salary desc
;

-- 풀이2
select t1.emp_no, t1.first_name, t2.dept_name, t3.max_salary
from employees t1, departments t2, (
									select tt2.dept_no, tt1.emp_no, max(tt1.salary) as max_salary
									from salaries tt1, dept_emp tt2
									where tt1.emp_no = tt2.emp_no
										and tt1.to_date =  '9999-01-01'
										and tt2.to_date =  '9999-01-01'
									group by tt2.dept_no
                                    ) t3
where t1.emp_no = t3.emp_no
	and t2.dept_no = t3.dept_no
order by t3.max_salary desc
;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select t1.emp_no, t1.first_name, t3.salary, t4.avg_salary
from employees t1, dept_emp t2, salaries t3, 
	(
		select tt2.dept_no as dept_no, avg(tt1.salary) as avg_salary
		from salaries tt1 join dept_emp tt2 on tt1.emp_no = tt2.emp_no 
				and tt1.to_date = '9999-01-01' 
				and tt2.to_date = '9999-01-01' 
		group by tt2.dept_no
	) t4
where t1.emp_no = t2.emp_no
	and t1.emp_no = t3.emp_no
    and t2.dept_no = t4.dept_no
    and t2.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'	
    and t3.salary > t4.avg_salary
;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select t1.emp_no, t1.first_name, t4.first_name as 'manager name', t3.dept_name
from employees t1, dept_emp t2, departments t3, 
	(
		select tt1.dept_no, tt1.emp_no, tt2.first_name
		from dept_manager tt1, employees tt2
		where tt1.emp_no = tt2.emp_no
			and tt1.to_date = '9999-01-01'
	) t4
where t1.emp_no = t2.emp_no
	and t2.dept_no = t3.dept_no
    and t3.dept_no = t4.dept_no
    and t2.to_date = '9999-01-01'
order by t1.emp_no asc
;


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select t1.emp_no, t1.first_name, t2.title, t3.salary
from employees t1, titles t2, salaries t3, dept_emp t4
where t1.emp_no = t2.emp_no
	and t1.emp_no = t3.emp_no
    and t1.emp_no = t4.emp_no
    and t2.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'
    and t4.dept_no = (
					select tt2.dept_no
					from salaries tt1 join dept_emp tt2 on tt1.emp_no = tt2.emp_no
					where tt1.to_date = '9999-01-01'
						and tt2.to_date = '9999-01-01'
					group by tt2.dept_no
					order by avg(salary) desc
					limit 0, 1
					)
order by t3.salary desc
;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
select dept_name, max(avg_salary)
from (
	select tt3.dept_name as dept_name, avg(tt1.salary) as avg_salary
	from salaries tt1, dept_emp tt2, departments tt3
	where tt1.emp_no = tt2.emp_no
		and tt2.dept_no = tt3.dept_no
	group by tt3.dept_name
    ) t1
;

-- 문제7.
-- 평균 연봉이 가장 높은 직책?
select t1.title, max(t1.avg_salary)
from (
		select tt2.title as title, avg(tt1.salary) as avg_salary
		from salaries tt1 join titles tt2 on tt1.emp_no = tt2.emp_no
		where tt1.to_date = '9999-01-01'
			and tt2.to_date = '9999-01-01'
		group by tt2.title
	) t1
;

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 매니저 연봉 순으로 출력합니다.

select t3.dept_name, t1.first_name, t4.salary, t5.first_name as 'manager name', t5.salary as 'manager salary'
from employees t1, dept_emp t2, departments t3, salaries t4,
	(
    select t1.emp_no as emp_no, t1.first_name as first_name, t2.dept_no as dept_no, t3.salary as salary
	from employees t1, dept_manager t2, salaries t3
	where t1.emp_no = t2.emp_no
		and t1.emp_no = t3.emp_no
		and t2.to_date = '9999-01-01'
		and t3.to_date = '9999-01-01'
    ) t5
where t1.emp_no = t2.emp_no
	and t2.dept_no = t3.dept_no
    and t1.emp_no = t4.emp_no
    and t2.dept_no = t5.dept_no
    and t2.to_date = '9999-01-01'
    and t4.to_date = '9999-01-01'
	and t4.salary > t5.salary
order by t1.emp_no asc
;

