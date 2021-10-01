-- 함수: 문자열 함수
-- upper
select upper('buSaN'), upper('busan');
select upper(first_name) from employees;

-- lower
select lower('buSaN'), lower('BUSAN');
select lower(first_name) from employees;

-- substring(문자열, index, length)
select substring('Hello World', 5, 5);

	-- 예제: 1989년에 입사한 사원들의 이름, 입사일 출력
	select first_name, hire_date
	from employees
	where substring(hire_date, 1, 4) = '1989'
	;

-- lpad(오른쪽 정렬), rpad(왼쪽 정렬)
select lpad('1234', 10, '-');
select lpad('1234', 5, '-');

select rpad('1234', 10, '-');
select rpad('1234', 5, '-');

	-- 예제: 직원들의 월급을 오른쪽 정렬로 출력해보자. 빈 공간은 '*' 로 채운다
	select lpad(salary, 10, '*') from salaries;

-- trim, ltrim, rtrim
select concat('---', ltrim('     hello     '), '---') as ltrim,
		concat('---', rtrim('     hello     '), '---') as rtrim,
        concat('---', trim(both ' ' from '     hello     '), '---') as trim;

select concat('---', trim(both 'X' from 'XXXXXhelloXXXXX'), '---') as trim
        

