package day04.Queue;

/**
 * 循环队列
 * 本类判断队空或队满是通过设置了一个flag变量,来表示队空或队满
 * 另一种方式是舍弃一个空间,当(rear + 1) % MAX_LENGTH == front % MAX_LENGTH表示队满,
 * front % MAX_LENGTH == rear % MAX_LENGTH表示队空
 * 从队首(front)出队,从队尾(rear)入队
 * 队首和队尾之间是所入队的元素,即队列长度: (rear - front + MAX_LENGTH) % MAX_LENGTH;
 * rear - front原因是,rear入队,逻辑上是队首在前,但是队尾进入元素,所以实际上队尾应始终在队首前
 */
public class CircleQueue {
    private Object[] head;//队列
    private Integer front;//队头
    private Integer rear;//队尾
    private static final Integer MAX_LENGTH = 10;//队列长度
    private boolean flag;//判断队列是否已满

    /**
     * 初始化队列
     */
    public CircleQueue(){
        this.head = new Object[MAX_LENGTH];
        this.front = 0;
        this.rear = 0;
        this.flag = false;
    }

    /**
     * 入队
     * @param val
     * @return
     */
    public synchronized boolean offer(Object val){
        Object[] temp = this.head;
        if(flag){
            System.out.println("队列已满,入队失败!");
            return false;
        }
        temp[rear % MAX_LENGTH] = val;
        rear++;
        if(rear % MAX_LENGTH == front % MAX_LENGTH){
            flag = true;
        }
        return true;
    }

    /**
     * 出队
     * @return
     */
    public synchronized Object poll(){
        Object[] temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空,无法出队!");
            return null;
        }
        Object data = temp[front % MAX_LENGTH];
        front++;
        return data;
    }

    /**
     * 获取队首元素
     * @return
     */
    public Object peek(){
        Object[] temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空,无法出队!");
            return null;
        }
        Object data = temp[front % MAX_LENGTH];
        return data;
    }

    /**
     * 查找元素的位置
     * @param val
     * @return
     */
    public Integer search(Object val){
        Object[] temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空,查找失败!");
            return -1;
        }
        for(int i = front % MAX_LENGTH; i < front % MAX_LENGTH + length(); i++){
            if(temp[i % MAX_LENGTH].equals(val)){
                return i - front % MAX_LENGTH + 1;
            }
        }
        return -1;
    }

    /**
     * 获取队列长度
     * @return
     */
    public Integer length(){
        if(flag){
            return MAX_LENGTH;
        }else{
            Integer length = (rear - front + MAX_LENGTH) % MAX_LENGTH;
            return length;
        }
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        if(front % MAX_LENGTH == rear % MAX_LENGTH && flag == false){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 打印队列
     */
    public void show(){
        Object[] temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空!");
            return;
        }
        System.out.println("队首-->");
        for(int i = front % MAX_LENGTH; i < front % MAX_LENGTH + length(); i++){
            System.out.println((i - front % MAX_LENGTH + 1) + ": " + temp[i % MAX_LENGTH]);
        }
        System.out.println("<--队尾");
    }
}
