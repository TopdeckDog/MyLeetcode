# 本题可以通过left join模拟not in子查询
SELECT
    name
FROM
    salesperson
left JOIN
    (
        select
            sales_id
        FROM
            orders
        join
            company
        on
            orders.com_id = company.com_id and company.name = "RED"
    ) t2
on
    salesperson.sales_id = t2.sales_id
where t2.sales_id is null;