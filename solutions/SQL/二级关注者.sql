# 我的思路:过滤在被关注着中本身不是关注者的人,然后根据被关注者被关注的人数排序
select
    followee as follower,
    count(distinct follower) num
from
    follow
where
    followee in (select follower from follow)
group by
    followee
order by
    followee;
