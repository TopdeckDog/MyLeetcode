# 我的思路:1.查询产品总数2.查询customer表中安用户分类购买的产品总数(去重)等于产品表产品总数的人

select
    customer_id
from
    Customer
group by
    customer_id
having
    count(distinct(product_key)) =
    (
        select
        count(*)
    from
        Product
    );


# 支持hivesql的版本
select
    customer_id
from
    (
        select
            customer_id,
            count(distinct(product_key)) product_cnt
        from
            Customer
        group by
            customer_id
    ) t1
join
    (
        select
            count(*) product_cnt
        from
            Product
    ) t2
on
    t1.product_cnt = t2.product_cnt;
# 如下也是支持hive的版本,但是有个错误,在group by时里面查询的列要么是被聚合后的,要么是group by的列,否则查询不出来
select
    customer_id
from
    Customer,
    (
        select
            count(*) product_cnt
        from
            Product
    ) t1
group by
    customer_id
having
    count(distinct(product_key)) = t1.product_cnt;
