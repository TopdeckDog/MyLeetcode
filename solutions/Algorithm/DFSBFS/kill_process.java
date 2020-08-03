package Algorithm.DFSBFS;
import java.util.*;

public class kill_process {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // 思路:bfs,找到该进程的子进程,放入队列中
        // 细节优化:
        // 1.索引:提前建立pid和ppid的索引,以免每次搜索某个进程的子进程时需要重新遍历ppid,用空间换时间
        Map<Integer, List<Integer>> dict = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (!dict.containsKey(ppid.get(i))) {
                dict.put(ppid.get(i), new ArrayList<>());
            }
            dict.get(ppid.get(i)).add(pid.get(i));
        }
        Queue<Integer> queue= new LinkedList<>();
        queue.offer(kill);
        List<Integer> res = new ArrayList<>();
        while (queue.size() > 0) {
            int cid = queue.poll();
            res.add(cid);
            List<Integer> sids = dict.get(cid);
            if (sids != null) {
                for (Integer sid : sids) {
                    queue.offer(sid);
                }
            }

        }
        return res;

    }
}