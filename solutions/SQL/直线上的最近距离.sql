
# 我的思路:求每个值的和前一个值的差值,然后排序
select
    x - lead(x, 1, -1232312) over(order by x desc) as shortest
from
    point
order by
    shortest
limit 1;

# 解法2:
SELECT
    MIN(ABS(p1.x - p2.x)) AS shortest
FROM
    point p1
        JOIN
    point p2 ON p1.x != p2.x