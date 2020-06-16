select
    cast(sum(TIV_2016) as decimal(10, 2)) TIV_2016
FROM
    insurance
where
    PID not in
    (
        select
            max(PID) PID
        FROM
            insurance
        group by
            TIV_2015
        having
            count(*) = 1
    ) and PID
    in
    (
        select
            max(PID) PID
        from
            insurance
        group by
            LAT,LON
        having
            count(*) = 1
    );

# 解法2:通过连接查询模拟in子查询
SELECT sum(A.TIV_2016) as `TIV_2016`
FROM
(
	SELECT DISTINCT A.*
	FROM
	insurance AS A
	JOIN insurance AS B ON (B.PID != A.PID AND B.TIV_2015 = A.TIV_2015)
) AS A
LEFT JOIN
(
	SELECT DISTINCT A.pid
	FROM
	insurance AS A
	JOIN insurance AS B ON (B.PID != A.PID AND B.LAT = A.LAT AND B.LON = A.LON)
) AS B
	ON (A.pid = B.pid)
WHERE B.pid IS NULL
