-- 함수: 날짜 함수
-- CURDATE(), CURRENT_DATE: 금일
select CURDATE(), CURRENT_DATE;

-- CURTIME, CURRENT_TITME: 현시각
select CURTIME(), CURRENT_TIME;

-- now(), sysdate()
select now(), sysdate();
select now(), sleep(2), now();					-- 똑같음: 쿼리가 시작됐을 때의 시간
select sysdate(), sleep(60), sysdate();			-- 다름: 함수가 호출됐을 때의 시간

-- date_format(date, format)
select date_format(now(), '%Y년 %m월 %d일 %H시 %i분 %s초') '현재 시간';
select date_format(now(), '%Y년 %c월 %d일 %H시 %i분 %s초') '현재 시간';


-- period_diff: 날 사이의 차이
-- YYMM, YYYYMM의 형식으로 비교
-- 예제: 근무 개월 수를 출력해보자
select first_name,
	period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) as month
from employees
order by month desc
;

-- date_add(=adddate), date_sub(=subdate)
-- 날짜를 date에 type(day, month, year) 형식의 표현식을 더하거나 뺀다.
-- 예제: 각 사원들의 근무 년수가 5년이 되는 날은 언제인가?
select first_name, 
	hire_date, 
	date_add(hire_date, interval 5 year)
from employees
;

-- cast
select '12345' + 10, cast('12345' as int) + 10;
select date_format(cast('2021-10-01' as date), '%Y-%m-%d') as '날짜 변환';
select cast(1-2 as unsigned);							-- ?????
select cast(cast(1-2 as unsigned) as signed);



