package 字节跳动;

import java.util.*;
public class 产品经理的IDEA {
    public static Task[] tasks;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int P = sc.nextInt();
            tasks = new Task[P];
            for (int i = 0; i < P; i ++) {
                tasks[i] = new Task(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            }

            printFinishTime(N, M, P);
        }
    }

    // 1.初始化程序员的优先队列,堆顶是当前程序员可以开工的时间

    // 3.随着程序员做完task,将时间推移到当前程序员可以开工的时间,然后每次推移都更新PM的堆,将多个PM中的多个最想完成的任务进行排序,做时间最短的那个
    public static void printFinishTime(int N, int M, int P) {
        // 1.初始化程序员的优先队列,堆顶是当前程序员可以开工的时间
        Queue<Integer> coders = new PriorityQueue<>((coder1, coder2) -> {
            return coder1 - coder2;
        });
        for (int i = 0; i < M; i++) coders.offer(0);

        // 2.初始化PM的优先队列,堆顶是当前PM最希望做的task
        Queue<Integer>[] pmQueue = new Queue[N];
        for (int i = 0; i < N; i++) {
            pmQueue[i] = new PriorityQueue<>((task1, task2) -> {
                if (tasks[task1].pri != tasks[task2].pri) {
                    return tasks[task2].pri - tasks[task1].pri;
                }
                if (tasks[task1].costTime != tasks[task2].costTime) {
                    return tasks[task1].costTime - tasks[task2].costTime;
                }
                return tasks[task1].comeupTime - tasks[task2].comeupTime;
            });
        }

        // 3.随着程序员做完task,将时间推移到当前程序员可以开工的时间,然后每次推移都更新PM的堆,将多个PM中的多个最想完成的任务进行排序,做时间最短的那个
        Integer[] comeupTasks = new Integer[P];
        for (int i = 0; i < P; i++) comeupTasks[i] = i;
        Arrays.sort(comeupTasks, (task1, task2) -> {
            return tasks[task1].comeupTime - tasks[task2].comeupTime;
        });

        int finishedTaskNum = 0;  // 代表已完成的任务数量
        int readyTaskNum = 0;  // 代表已经准备好的任务数量(可能已经完成了)
        while (finishedTaskNum < P) {
            System.out.println("hahaha");
            int nextTask = -1;
            // 程序员去PM队列中取出任务
            for (int j = 0; j < N; j++) {
                if (!pmQueue[j].isEmpty()) {
                    int pmMostTask = pmQueue[j].peek();
                    if (nextTask == -1) {
                        nextTask = pmMostTask;
                        continue;
                    }
                    if (tasks[nextTask].costTime != tasks[pmMostTask].costTime) {
                        nextTask = (tasks[nextTask].costTime > tasks[pmMostTask].costTime) ? pmMostTask : nextTask;
                        continue;
                    }
                    if (tasks[nextTask].pmID != tasks[pmMostTask].pmID) {
                        nextTask = (tasks[nextTask].pmID > tasks[pmMostTask].pmID) ? pmMostTask : nextTask;
                    }
                }
            }
            // 取不到任务,则将程序员的时间全部延后到直到能取到任务
            int curTime = 0;
            if (nextTask == -1) {
                int nextTaskBeginTime = tasks[comeupTasks[readyTaskNum]].comeupTime;
                delayCodersTime(nextTaskBeginTime, coders);
                curTime = nextTaskBeginTime;
            } else {
                pmQueue[tasks[nextTask].pmID - 1].poll();
                tasks[nextTask].finishTime = coders.poll() + tasks[nextTask].costTime;
                coders.offer(tasks[nextTask].finishTime);
                finishedTaskNum++;
                curTime = coders.peek();
            }
            readyTaskNum += timeMove(curTime, readyTaskNum, comeupTasks, pmQueue);
        }

        for (Task task : tasks) {
            System.out.println(task.finishTime);
        }


    }

    // 推迟程序员的开工时间,让最小的程序员的开工时间都到达下一个task提出的时间
    public static void delayCodersTime(int nextTaskBeginTime, Queue<Integer> coders) {
        while (coders.peek() < nextTaskBeginTime) {
            coders.poll();
            coders.offer(nextTaskBeginTime);
            System.out.println("wuwuwuwu");
        }
    }

    // 当前时间往后移动,返回因为这次移动而新增的提出的TASK
    public static int timeMove(int curTime, int readyTaskNum, Integer[] comeupTasks, Queue<Integer>[] pmQueue) {
        int newReadyTaskNum = 0;
        while (readyTaskNum < comeupTasks.length && tasks[comeupTasks[readyTaskNum]].comeupTime <= curTime) {
            int nextTask = comeupTasks[readyTaskNum++];
            pmQueue[tasks[nextTask].pmID - 1].offer(nextTask);  // pmID需要-1
            newReadyTaskNum++;
            System.out.println("xixixi");
        }
        return newReadyTaskNum;
    }



}
class Task {
    int pmID;
    int comeupTime;
    int pri;
    int costTime;
    int finishTime;

    public Task(int pmID, int comeupTime, int pri, int costTime) {
        this.pmID = pmID;
        this.comeupTime = comeupTime;
        this.pri = pri;
        this.costTime = costTime;
    }
}