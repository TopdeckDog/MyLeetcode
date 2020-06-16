#https://leetcode-cn.com/problems/shortest-distance-in-a-plane/
# 我的思路:1.笛卡尔积2.按条件过滤相同点
select
    cast(power(power(p1.x - p2.x, 2) + power(p1.y - p2.y, 2), 0.5) as decimal(10, 2)) as distance
from
    point_2d p1 join point_2d p2
where
    (p1.x != p2.x) or (p1.y != p2.y)
order by
    distance
limit 1;


标准答案:1.非等值连接2.避免重复计算,之间的计算可能会重复计算a,b和b,a
SELECT
    ROUND(SQRT(MIN((POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2)))),2) AS shortest
FROM
    point_2d p1
        JOIN
    point_2d p2 ON (p1.x <= p2.x AND p1.y < p2.y)
        OR (p1.x <= p2.x AND p1.y > p2.y)
        OR (p1.x < p2.x AND p1.y = p2.y)
