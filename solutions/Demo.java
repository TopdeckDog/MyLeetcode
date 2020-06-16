import java.sql.Timestamp;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Interval>> schedule = new ArrayList<>();
        List<Interval> schedule1 = new ArrayList<>();
        schedule1.add(new Interval(1, 2));
        schedule1.add(new Interval(5, 6));
        schedule.add(schedule1);
        List<Interval> schedule2 = new ArrayList<>();
        schedule2.add(new Interval(1, 3));
        schedule.add(schedule2);
        List<Interval> schedule3 = new ArrayList<>();
        schedule3.add(new Interval(4, 10));
        schedule.add(schedule3);

        s.employeeFreeTime(schedule);
    }
}


class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};


class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // 思路:用一个链表表示不重复的一个区间,每一来一个区间,判断是否与链表中区间有重合,没有则插入,有则融合
        // 以下是连续工作的区间
        List<Interval> workTime = new LinkedList<Interval>();
        for (List<Interval> oneWorkTime : schedule) {
            for (Interval interval : oneWorkTime) {
                if (workTime.isEmpty()) {
                    workTime.add(interval);
                } else {
                    int index = 0;
                    boolean insertFlag = true;
                    for (Iterator<Interval> iter = workTime.iterator(); iter.hasNext(); index++) {
                        Interval curWorkTime = iter.next();
                        if (interval.end < curWorkTime.start) {
                            break;
                        } else if (interval.start > curWorkTime.end) {
                            continue;
                        } else {
                            interval.start = Math.min(interval.start, curWorkTime.start);
                            interval.end = Math.max(interval.end, curWorkTime.end);

                            insertFlag = false;
                            break;
                        }
                    }
                    if (insertFlag) {
                        workTime.add(index, interval);
                    }
                }
            }
        }

        List<Interval> freeTime = new LinkedList<Interval>();
        int lastWorkEndTime = -1;
        for (Iterator<Interval> iter = workTime.iterator(); iter.hasNext();) {
            Interval curWorkTime = iter.next();
            if (lastWorkEndTime != -1) {
                freeTime.add(new Interval(lastWorkEndTime, curWorkTime.start));
            }
            lastWorkEndTime = curWorkTime.end;
        }
        return freeTime;
    }
}