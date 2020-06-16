# 注意当表为空时max()等聚合函数得到的是null
# 我的思路:先过滤掉只有一个的记录再求最大周
# 以下实现是错误的,
select
    num
from
    (
        select
            num,
            count(*) cnt
        from
            my_numbers
        group by
            num
    ) t1
where
    t1.cnt = 1
order by
    num desc
limit 1;

# 解法1:和我的思路一样,但是将过滤条件放在了having中
select
    max(num) num
from
    (
        select
            num
        from
            my_numbers
        group by
            num
        having
            count(*) = 1
    ) t1;
