# 思路:根据聚合人数排名,然后输出排名为1的项目
# 知识点:窗口函数中可根据聚合函数的结果排序
select
    project_id
from
    (
        select
            project_id,
            rank() over(order by count(*) desc) rk
        from
            Project
        group by
            project_id
    ) t1
where rk = 1;

# 其他思路:having中过滤结果
select
    project_id
from
    project
group by
    project_id
having
    count(project_id) =
    (
        select count(1) as num
        from Project
        group by project_id
        order by count(1) desc limit 1
    )

作者：cabbage-12
链接：https://leetcode-cn.com/problems/project-employees-ii/solution/mysqlkao-lu-duo-ge-zui-da-zhi-de-qing-kuang-by-cab/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

Create table If Not Exists Project (project_id int, employee_id int);
Create table If Not Exists Employee (employee_id int, name varchar(10), experience_years int);
Truncate table Project;
insert into Project (project_id, employee_id) values ('1', '1');
insert into Project (project_id, employee_id) values ('1', '2');
insert into Project (project_id, employee_id) values ('1', '3');
insert into Project (project_id, employee_id) values ('2', '1');
insert into Project (project_id, employee_id) values ('2', '4');
Truncate table Employee;
insert into Employee (employee_id, name, experience_years) values ('1', 'Khaled', '3');
insert into Employee (employee_id, name, experience_years) values ('2', 'Ali', '2');
insert into Employee (employee_id, name, experience_years) values ('3', 'John', '1');
insert into Employee (employee_id, name, experience_years) values ('4', 'Doe', '2');