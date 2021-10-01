-- 함수: 수학
-- abs: 절대값
select abs(-1), abs(1);

-- mod: 나머지 값
select mod(10,3);

-- floor: 내림값
select floor(3.14);

-- ceil: 올림값
select ceil(3.14);
select ceiling(3.14);

-- round(x): 반올림값
-- round(x, d): x값 중에 소수점 d자리에 가장 근접한 실수값
select round(3.14);
select round(3.6);

select round(1.498123579, 0);
select round(1.498123579, 1);
select round(1.498123579, 2);
select round(1.498123579, 3);

-- pow(x, y), power(x, y): x의 y승
select pow(2, 10), power(10, 2); 

-- sign(x)
select sign(20), sign(-100), sign(0);

-- greatest(x, y, ...), least(x, y, ...)
select greatest(10, 20, 30, 40), least(10, 20, 30, 40);
select greatest('b', 'a', 'c'), greatest('hello', 'hellq', 'hellp');


