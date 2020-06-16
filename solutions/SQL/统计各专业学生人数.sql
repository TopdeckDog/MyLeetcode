# 1.查询每个系续学生人数
# 2.和系表联合查询,然后按照学生人数排序
# 关键点:默认值的设置(nvl)
select
    dept_name,
    ifnull(student_number, 0) as student_number
from
(
    select
        dept_id,
        count(*) student_number
    from
        student
    group by
        dept_id
) t1
right join
    department
on
    t1.dept_id = department.dept_id
order by
    student_number desc, dept_name;

# 优化版本:少了一层子查询,并且注意细节
SELECT
    dept_name, COUNT(student_id) AS student_number
FROM
    department
        LEFT OUTER JOIN
    student ON department.dept_id = student.dept_id
GROUP BY department.dept_name
ORDER BY student_number DESC , department.dept_name
