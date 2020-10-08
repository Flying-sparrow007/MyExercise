package day03;

/**
 * 链栈(单链表),其他单链表构成的链栈同理,先进后出
 */
public class LinkedStack {
    private Node head;//链栈的头
    private Integer size;//链栈的长度

    public LinkedStack(){
        this.head = null;
        size = 0;
    }

    class Node{
        private Object data;
        private Node next;

        public Node(Object val){
            this.data = val;
            this.next = null;
        }
    }

    /**
     * 入栈
     * @param val
     * @return
     */
    public synchronized boolean push(Object val){
        Node temp = this.head;
        Node newNode = new Node(val);
        if(temp == null){
            head = newNode;
            temp = head;
            head.next = null;
            size++;
            return true;
        }else{
            newNode.next = temp;
            head = newNode;
            size++;
            return true;
        }
    }

    /**
     * 出栈
     * @return
     */
    public synchronized Object pop(){
        Node temp = this.head;
        if(isEmpty()){
            System.out.println("栈为空,出栈失败!");
            return -1;
        }
        if(temp.next == null){
            head = null;
            size--;
            return temp.data;
        }else {
            head = temp.next;//栈顶元素出栈
            temp.next = null;//断开被出栈元素与链表的连接
            size--;
            return temp.data;
        }
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public synchronized Object peek(){
        Node temp = this.head;
        if(isEmpty()){
            System.out.println("栈为空,获取失败!");
            return -1;
        }
        return temp.data;
    }

    /**
     * 查找指定元素的位置
     * @param val
     * @return
     */
    public synchronized Integer search(Object val){
        Node temp = this.head;
        Integer i = 1;
        if(isEmpty()){
            System.out.println("栈为空,出栈失败!");
            return -1;
        }
        while (i <= size){
            if(temp.data.equals(val)){
                return i;
            }
            i++;
            temp = temp.next;
        }
        return -1;
    }

    /**
     * 获取链表长度
     * @return
     */
    public synchronized Integer length(){
        return size;
    }

    /**
     * 判断链栈是否为空
     * @return
     */
    public synchronized boolean isEmpty(){
        Node temp = this.head;
        return temp == null ? true : false;
    }

    /**
     * 打印链表
     */
    public synchronized void show(){
        Node temp = this.head;
        Integer i = 1;
        if(size < 1){
            System.out.println("栈为空!");
            return ;
        }
        System.out.println(i + ": " +temp.data);
        while (temp.next != null){
            temp = temp.next;
            i++;
            System.out.println(i + ": " +temp.data);
        }
    }

}
