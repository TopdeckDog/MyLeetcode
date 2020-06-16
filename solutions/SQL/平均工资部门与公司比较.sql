
# 我的思路:1.求出公司每个月的平均工资2.求出部门每个月的平均工资3.联合查询
select
    t1.pay_month pay_month,
    t1.department_id department_id,
    case when (t1.depart_mon_avg > t2.mon_avg) then 'higher'
         when (t1.depart_mon_avg < t2.mon_avg) then 'lower'
        else 'same' end as comparison
from
    (
        select
            date_format(pay_date, '%Y-%m') as pay_month,
            department_id,
            avg(amount) depart_mon_avg
        from
            salary
        join
            employee
        on
            salary.employee_id = employee.employee_id
        group by
            date_format(pay_date, '%Y-%m'), employee.department_id
    ) t1
join
    (
        select
            avg(amount) mon_avg,
            date_format(pay_date, '%Y-%m') as pay_month
        from
            salary
        group by
            date_format(pay_date, '%Y-%m')
    ) t2
on t1.pay_month = t2.pay_month;