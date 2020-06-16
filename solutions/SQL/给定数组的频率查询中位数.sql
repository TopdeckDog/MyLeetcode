# 方法1:通过窗口函数,关键点:求出数的排名范围
select
    cast(avg(number) as float) median
from
(
    select
        number,
        sum(Frequency) over(order by number) - Frequency as prev_sum,
        sum(Frequency) over(order by number) as cur_sum
    from
        Numbers
) t1,
(
    select
        sum(Frequency) as cnt
    from
        Numbers
) t2
where
    t1.prev_sum <= (t2.cnt / 2) and t1.cur_sum >= (t2.cnt / 2);

# 方法2:通过sum(if)进行统计模拟范围
SELECT
  AVG(t.Number) median
FROM(
  SELECT
    n1.Number,
    SUM(IF(n2.Number < n1.Number, n2.Frequency, 0)) counts_less,
    SUM(IF(n2.Number > n1.Number, n2.Frequency, 0)) counts_greater,
    s.n
  FROM
    Numbers n1, Numbers n2, (SELECT SUM(Frequency) n FROM Numbers) s
  GROUP BY
    n1.Number
  HAVING
    counts_less <= FLOOR(s.n/2)
    AND counts_greater <= FLOOR(s.n/2)
) t;