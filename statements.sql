SELECT scheduleId,routeId FROM `schedule`
WHERE scheduleId in(SELECT scheduleId FROM timetable WHERE stopName='北京南' AND leaveAt like '2016-11-11%')
AND routeId in (SELECT routeId FROM route where route.stopNames like '%北京南%南京南%');


select seattype.type as type, seattype.seats-IFNULL(sold,0) as remain FROM
( select type,ifnull(MAX(amount),0) as sold
  from sold_amount
  WHERE scheduleId = 1 AND stopName in ('徐州东','南京南','镇江南')
  GROUP BY type
) t RIGHT JOIN seattype ON seattype.type = t.type;

SELECT id,carriageNum,seatNum FROM seat WHERE type='一等座'
AND id NOT in(
  SELECT seatId FROM seats_sold
  WHERE stopName in ('徐州东','南京南','镇江南')
)ORDER BY RAND() LIMIT 1;