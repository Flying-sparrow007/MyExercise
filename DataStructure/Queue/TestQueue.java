package day04.Queue;

/**
 * 测试队列
 */
public class TestQueue {
    public static void main(String[] args) {
        TestQueue t = new TestQueue();

        //队列
        //t.useQueue();

        //双端队列
        //t.useDeque();

        //循环队列
        t.useCircleQueue();
    }

    /**
     * 使用队列
     */
    private void useQueue(){
        Queue queue = new Queue();

        //入队
        queue.offer("第一个节点");
        queue.offer("第二个节点");
        queue.offer("第三个节点");
        queue.offer("第四个节点");
        queue.offer("第五个节点");

        //出队
        /*System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());*/

        //获取队列第一个元素
        /*System.out.println(queue.peek());
        System.out.println(queue.peek());*/

        //查找队列元素
        System.out.println(queue.search(2));

        //获取队列长度
        System.out.println(queue.length());

        //判断队列是否为空
        System.out.println(queue.isEmpty());

        //打印队列
        queue.show();
    }

    private void useDeque(){
        Deque deque = new Deque();

        //队首入队
        deque.firstOffer("第一个节点");
        deque.firstOffer("第二个节点");
        deque.firstOffer("第三个节点");

        //队尾入队
        deque.lastOffer("第四个节点");
        deque.lastOffer("第五个节点");
        deque.lastOffer("第六个节点");

        //队首出队
        //System.out.println(deque.firstPoll());

        //队尾出队
        //System.out.println(deque.lastPoll());

        //获取队首元素
        /*System.out.println(deque.firstPeek());
        System.out.println(deque.firstPeek());*/

        //获取队尾元素
        /*System.out.println(deque.lastPeek());
        System.out.println(deque.lastPeek());*/

        //查找队列元素
        System.out.println(deque.search(2));

        //获取队列长度
        System.out.println(deque.length());

        //判断队列是否为空
        System.out.println(deque.isEmpty());

        //打印队列
        deque.show();
    }

    /**
     * 使用循环队列
     */
    public void useCircleQueue(){
        CircleQueue cq = new CircleQueue();

        //入队
        cq.offer(1);
        cq.offer(2);
        cq.offer(3);
        cq.offer(4);
        cq.offer(5);
        cq.offer(6);
        cq.offer(7);
        /*cq.offer(8);
        cq.offer(9);
        cq.offer(10);*/
        //cq.offer(11);

        //出队
        //System.out.println(cq.poll());

        //获取队首元素
        /*System.out.println(cq.peek());
        System.out.println(cq.peek());*/

        //查找元素的位置
        System.out.println(cq.search(56));
        System.out.println(cq.search(6));

        //获取队列长度
        System.out.println(cq.length());

        //判断队列是否为空
        System.out.println(cq.isEmpty());

        //打印队列
        cq.show();
    }

}
