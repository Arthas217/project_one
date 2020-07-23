package org.com.zlk.leedcode.company.priorityqueue;

/**
 * 添加进PriorityBlockingQueue的元素必须实现Comparable接口
 * 实现Comparable接口的类支持排序
 * @Author zc217
 * @Date 2020/7/1
 */
public class Event implements Comparable<Event> {
    private int thread;
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * 元素的属性priority输出是从大到小
     */
    @Override
    public int compareTo(Event e) {
        if (this.priority < e.getPriority()) {
            return 1;
        } else if (this.priority > e.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }

//    /**
//     * 元素的属性priority自然排序
//     */
//    @Override
//    public int compareTo (Event e) {
//        if (this.priority < e.getPriority()) {
//            return -1;
//        } else if (this.priority > e.getPriority()) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }

}
