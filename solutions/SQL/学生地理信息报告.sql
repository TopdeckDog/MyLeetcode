# 重点看,这题我没做出来

# 解法1:通过将三张单列的表联合起来形成
select America,Asia,Europe
from(
    select row_number() over(order by name) as rn,name as America from student
    where continent='America'
) a
left join(
    select row_number() over(order by name) as rn,name as Asia from student
    where continent='Asia'
) b on a.rn=b.rn
left join(
    select row_number() over(order by name) as rn,name as Europe from student
    where continent='Europe'
) c on a.rn=c.rn

# 解法2:通过对排名分组实现
select
    max(case continent when 'America' then name else null end) as America,
    max(case continent when 'Asia' then name else null end) as Asia,
    max(case continent when 'Europe' then name else null end) as Europe
from
    (
        select
            name,
            continent,
            row_number() over(partition by continent order by name) as rk
        from
            student
    ) t1
group by
    rk;
