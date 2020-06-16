https://leetcode-cn.com/problems/get-highest-answer-rate-question/
# 巧用对聚合的结果排序
select
    question_id as survey_log
from
    survey_log
group by
    question_id
order by
    sum(if(action = 'answer', 1, 0)) / sum(if(action = 'show', 1, 0)) desc
limit 1;