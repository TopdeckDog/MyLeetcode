# 解法1:使用case when流程控制
select
    t1.id,
    case when t1.p_id is null then "Root"
        when t2.p_id is null then "Leaf"
        else "Inner" end `Type`
from
    tree t1
left join
    (
        select
            p_id
        from
            tree
        group by
            p_id
    ) t2
on
    t1.id = t2.p_id;

# 解法2:使用union经结果拼起来
SELECT
    id, 'Root' AS Type
FROM
    tree
WHERE
    p_id IS NULL

UNION

SELECT
    id, 'Leaf' AS Type
FROM
    tree
WHERE
    id NOT IN (SELECT DISTINCT
            p_id
        FROM
            tree
        WHERE
            p_id IS NOT NULL)
        AND p_id IS NOT NULL

UNION

SELECT
    id, 'Inner' AS Type
FROM
    tree
WHERE
    id IN (SELECT DISTINCT
            p_id
        FROM
            tree
        WHERE
            p_id IS NOT NULL)
        AND p_id IS NOT NULL
ORDER BY id;
