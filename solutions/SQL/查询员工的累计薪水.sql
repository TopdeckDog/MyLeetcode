
# 先过滤掉每个员工的最近一个月薪水记录,在开窗计算每个月最近三个月的累计薪水
select
    Id,
    Month,
    sum(Salary) over(partition by Id order by Month desc range between current row and 2 following) Salary
from
(
    select
        Employee.Id,
        Employee.Month,
        Salary
    from
        (
            select
                Id,
                max(Month) cur_month
            from
                Employee
            group by
                Id
        ) t1
        join
            Employee
        on
            t1.Id = Employee.Id
    where
        Employee.Month < t1.cur_month
) t2;

# 先过滤掉每个员工最近一个月的记录,然后对每个月查询它最近三个月的记录,最后分组进行聚合
select Id, AccMonth as Month, sum(Salary) as Salary
from
(
    select a.Id as Id, a.Month as AccMonth, b.Month as Month, b.Salary as Salary
    from
    (
        select Employee.Id as Id, Employee.Month as Month
        from Employee, (select Id, max(Month) as Month
            from Employee
            group by Id) as LastMonth
            where Employee.Id = LastMonth.Id and Employee.Month != LastMonth.Month) as a
    join Employee as b
    on a.Id = b.Id and a.Month - b.Month <= 2 and a.Month - b.Month >= 0
) as acc
group by Id, AccMonth
order by Id, Month desc

