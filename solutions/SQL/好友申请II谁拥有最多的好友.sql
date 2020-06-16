# 本题关键在于union all的使用,不能使用联合查询,因为两个表都可能key为null
select ids as id, count(*) as num
   from
   (
        select requester_id as ids from request_accepted
        union all
        select accepter_id from request_accepted
    ) as tbl1
   group by ids
   ) as tbl2
order by num desc
limit 1;


with tmp as
(
    select
        distinct requester_id,  accepter_id
    from
        request_accepted
)


# 以下写法错误,可能requester_id没有的id但是accepter_id有的id则会漏
select
    t1.requester_id as id,
    (ifnull(send_friends, 0) + ifnull(recv_friends, 0)) as num
from
(
    select
        requester_id,
        count(*) recv_friends
    from
        tmp
    group by
        requester_id
) t1
left join
(
    select
        accepter_id,
        count(*) send_friends
    from
        tmp
    group by
        accepter_id
) t2 on
    t1.requester_id = t2.accepter_id
order by
    num desc
limit 1;