# 查询count(candidateId)为max的那一条记录，然后和Candidate表做联合查询
select
    Candidate.Name
from
(
    select
        t1.candidateId
    from
    (
        select
            candidateId,
            count(*) vote_cnt
        from
            Vote
        group by
            candidateId
    ) t1
    join
    (
        select
            max(vote_cnt) max_vote_cnt
        from
        (
            select
                candidateId,
                count(*) vote_cnt
            from
                Vote
            group by
                candidateId
        ) t1
    ) t2
    on
        t1.vote_cnt = t2.max_vote_cnt
)t3 join
    Candidate
on
    t3.candidateId = Candidate.id;



# 优化后的查询
select Name
from (
  select CandidateId as id
  from Vote
  group by CandidateId
  order by count(id) desc
  limit 1
) as Winner join Candidate
on Winner.id = Candidate.id
