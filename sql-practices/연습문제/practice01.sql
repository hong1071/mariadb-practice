-- 문제1.
-- 사번이 10944인 사원의 이름은(전체 이름)
	select emp_no, concat(first_name, ' ', last_name) as fullname
	from employees
	where emp_no = 10944
	;

-- 문제2. 
-- 전체직원의 다음 정보를 조회하세요. 가장 선임부터 출력이 되도록 하세요. 출력은 이름, 성별,  입사일 순서이고 “이름”, “성별”, “입사일" 컬럼 이름을 대체해 보세요.
	select concat(first_name, ' ', last_name) as 이름, gender as 성별, hire_date as 입사일
	from employees
	order by hire_date asc
	;

-- 문제3.
-- 여직원과 남직원은 각 각 몇 명이나 있나요?
	select gender, count(*)
    from employees
    group by gender
    ;

-- 문제4.
-- 현재 근무하고 있는 직원 수는 몇 명입니까? (salaries 테이블을 사용합니다.) 
-- to_date 사용
    select count(*)
    from
		(
		select emp_no, sum(salary)
		from salaries
		group by emp_no
		) t1
	;
    
-- 문제5.
-- 부서는 총 몇 개가 있나요?
	select * from dept_emp;
    select distinct dept_no from dept_emp;
	select count(*) from departments;

-- 문제6.
-- 현재 부서 매니저는 몇 명이나 있나요?(역임 매너저는 제외)
	select * 
    from dept_manager
    where to_date = '9999-01-01'
    ;
    
-- 문제7.
-- 전체 부서를 출력하려고 합니다. 순서는 부서이름이 긴 순서대로 출력해 보세요.
	select dept_name
    from departments
    order by char_length(dept_name) desc
    ;

-- 문제8.
-- 현재 급여가 120,000이상 받는 사원은 몇 명이나 있습니까?
	select count(*) 
	from salaries
    where to_date = '9999-01-01'
		and salary > 120000
    ;

-- 문제9.
-- 어떤 직책들이 있나요? 중복 없이 이름이 긴 순서대로 출력해 보세요.
	select title 
    from titles
    group by title
    order by char_length(title) desc
    ;

-- 문제10
-- 현재 Enginner 직책의 사원은 총 몇 명입니까?
	select count(*)
    from titles
    where title = 'Engineer'
		and to_date = '9999-01-01'
    ;
    
-- 문제11
-- 사번이 13250(Zeydy)인 직원이 직책 변경 상황을 시간순으로 출력해보세요.
-- title에서만 찾기
	select * 
    from titles
    where emp_no = 13250
    order by from_date asc
    ;

	select t1.emp_no, concat(first_name, ' ', last_name), hire_date, from_date, to_date
	from employees t1, titles t2
	where t1.emp_no = t2.emp_no
		and t1.emp_no = 13250
	order by from_date asc
    ;