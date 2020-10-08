package day04.Queue;

/**
 * 队列(单链表实现),其他链表类似
 */
public class Queue {
    private Node head;//头指针
    private Integer size;//队列长度

    /**
     * 初始化队列
     */
    public Queue(){
        this.head = null;
        this.size = 0;
    }

    class Node{
        private Object data;//存储数据
        private Node next;//存储下一个节点地址

        public Node(Object val){
            this.data = val;
            this.next = null;
        }
    }

    /**
     * 入队
     * @param val
     * @return
     */
    public synchronized boolean offer(Object val){
        Node newNode = new Node(val);
        Node temp = this.head;
        if(temp == null){
            head = newNode;
            temp = head;
            temp.next = null;
        }else{
            newNode.next = temp;
            //修改头head指向头节点
            head = newNode;
        }
        size++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public synchronized Object poll(){
        Node temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空,出队失败");
            return null;
        }
        while(temp.next.next != null){
            temp = temp.next;
        }
        Object data = temp.next.data;
        temp.next = null;
        size--;
        return data;
    }

    /**
     * 获取队列第一个元素
     * @return
     */
    public synchronized Object peek(){
        Node temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空,获取失败");
            return null;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        Object data = temp.data;
        return data;
    }

    /**
     * 查找队列元素
     * @param index
     * @return
     */
    public Object search(Integer index){
        Integer i = 1;
        Node temp = this.head;
        if(isEmpty()){
            System.out.println("队列为空,查找失败!");
            return null;
        }
        if(index < 1 || index > size){
            System.out.println("查找位置有误,请重新输入!");
            return null;
        }
        while (i <= size){
            //因为队尾是队列的出口,当i = 1时,实际上时查找链表最后一个元素,即size - i + 1
            if((size - i + 1) == index){
                return temp.data;
            }
            i++;
            temp = temp.next;
        }
        return null;
    }

    /**
     * 获取队列长度
     * @return
     */
    public synchronized Integer length(){
        return size;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public synchronized boolean isEmpty(){
        Node temp = this.head;
        return temp == null ? true : false;
    }

    /**
     * 打印队列
     */
    public void show(){
        Node temp = this.head;
        System.out.println("队尾-->");
        System.out.println("内容:" + temp.data
                + ", 后继:" + temp.next
                + ", 自身:" + temp
        );
        while(temp.next != null){
            temp = temp.next;
            System.out.println("内容:" + temp.data
                    + ", 后继:" + temp.next
                    + ", 自身:" + temp
            );
        }
        System.out.println("<--队首");
    }

}
