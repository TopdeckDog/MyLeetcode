select
    p.product_id,
    year first_year,
    quantity,
    price
from
    (
        select
            row_number() over(partition by product_id order by year) rk,
            product_id,
            year,
            quantity,
            price
        from
            Sales
    ) t1
join
    Product p
on
    p.product_id = t1.product_id
where
    t1.rk = 1;


# 优秀思路:分组排名用min函数,取top1
select
    t1.product_id,
    t1.first_year,
    quantity,
    price
from
    (
        select
            product_id,
            min(year) as first_year
        from
            Sales
        group by
            product_id
    ) t1
join
    Sales t2
on
    t1.product_id = t2.product_id and t1.first_year = t2.year;