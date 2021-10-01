-- 테이블간 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select t1.emp_no, concat(t1.first_name, ' ', t1.last_name), salary
from employees t1, salaries t2
where t1.emp_no = t2.emp_no
	and t2.to_date = '9999-01-01'
order by salary desc
;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select t1.emp_no, concat(t1.first_name, ' ', t1.last_name) as name, title
from employees t1, titles t2
where t1.emp_no = t2.emp_no
	and t2.to_date = '9999-01-01'
order by name asc
;

-- 문제3
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요.
select t1.emp_no, concat(t1.first_name, ' ', t1.last_name) as 'name', t3.dept_name
from employees t1, dept_emp t2, departments t3
where t1.emp_no = t2.emp_no
	and t2.dept_no = t3.dept_no
    and t2.to_date = '9999-01-01'
order by name asc
;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select t1.emp_no, concat(t1.first_name, ' ', t1.last_name) as 'name', t2.salary, t5.title, t4.dept_name
from employees t1, salaries t2 , dept_emp t3 , departments t4 , titles t5
where t1.emp_no = t2.emp_no
	and t1.emp_no = t3.emp_no
    and t3.dept_no = t4.dept_no
    and t1.emp_no = t5.emp_no
    and t2.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'
    and t5.to_date = '9999-01-01'
order by name asc
;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적(현재 빼기)이 있는 모든 사원의 사번과 이름을 출력하세요. (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.
select t1.emp_no, concat(t1.first_name, ' ', t1.last_name) as 'name'
from employees t1, titles t2
where t1.emp_no = t2.emp_no
	and title = 'Technique Leader'
    and t2.to_date != '9999-01-01'
;

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select concat(t1.first_name, ' ', t1.last_name) as 'name', t3.dept_name, t4.title
from employees t1, dept_emp t2, departments t3, titles t4
where t1.emp_no = t2.emp_no
	and t2.dept_no = t3.dept_no
    and t1.emp_no = t4.emp_no
	and t1.last_name like 'S%'
    and t2.to_date = '9999-01-01'
    and t4.to_date = '9999-01-01'
;

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select *
from employees t1, salaries t2, titles t3
where t1.emp_no = t2.emp_no
	and t1.emp_no = t3.emp_no
    and t2.salary >= 40000
    and t3.title = 'Engineer'
    and t3.to_date = '9999-01-01'
    and t2.to_date = '9999-01-01'
order by salary desc
;

-- 문제8.
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오
select t3.title, t2.salary
from employees t1, salaries t2, titles t3
where t1.emp_no = t2.emp_no
	and t1.emp_no = t3.emp_no
    and t2.salary > 50000
	and t2.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'
order by t2.salary desc
;

-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select dept_name, avg(salary)
from departments t1, salaries t2, dept_emp t3
where t1.dept_no = t3.dept_no
	and t2.emp_no = t3.emp_no
	and t2.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'
group by dept_name
order by avg(salary) desc
;

-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select t3.title, avg(t1.salary) as avgSal
from salaries t1, employees t2, titles t3
where t1.emp_no = t2.emp_no
	and t2.emp_no = t3.emp_no
	and t1.to_date = '9999-01-01'
    and t3.to_date = '9999-01-01'
group by t3.title
order by avgSal desc
;
