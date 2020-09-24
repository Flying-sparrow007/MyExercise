package day02;

/**
 * 带头节点的单链表
 */
public class SingleLink {
    /**链表类中持有节点的引用*/
    private Node head;
    private Integer size;//链表长度

    public SingleLink(){
        head = new Node();
    }

    class Node{
        Object data;//节点数据
        Node next;//下一个节点的地址

        /**头节点*/
        public Node(){
            this.data = "data";
            this.next = null;
            size = 1;
        }

        /**非头节点*/
        public Node(Object data){
            this.data = data;
            this.next = null;
        }
    }

    /**
     * 尾插法
     * @param val
     * @return
     */
    public Node insertTail(Object val){
        Node newNode = new Node(val);
        Node temp = null;
        temp = this.head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = null;
        size++;
        return newNode;
    }

    /**
     * 头插法
     * @param val
     * @return
     */
    public Node insertHead(Object val){
        Node newNode = new Node(val);
        Node temp = null;
        temp = this.head;
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
        return newNode;
    }


    /**
     * 在指定位置插入节点
     * @param val
     * @param index
     * @return
     */
    public Node insertNode(Object val, Integer index){
        Node newNode = new Node(val);
        Integer i = 0;
        Node temp = this.head;
        if(size <= 1){//只有头节点,即链表为空
            System.out.println("链表为空!");
            return null;
        }
        if(index < 1 || index > size){//插入位置小于头节点的位置或超过最后一个节点的下一个的下一个节点
            System.out.println("插入位置不合法");
            return null;
        }
        while(temp.next != null){
            //寻找所要插入位置的前驱节点
            if(i == index - 1){
                newNode.next = temp.next;
                temp.next = newNode;
                return newNode;
            }
            i++;
            temp = temp.next;
        }
        return null;
    }

    /**
     * 删除指定位置的节点
     * @param index
     * @return
     */
    public Node deleteNode(Integer index){
        Integer i = 0;
        Node temp = this.head;
        if(size <= 1){//只有头节点
            System.out.println("链表长度为空!");
            return null;
        }
        if(index < 1 || index > size - 1){//删除位置小于第一个非头节点或大于链表长度-1
            System.out.println("删除位置不合法!");
            return null;
        }
        while(temp.next != null){
            //寻找被删节点的前驱节点
            if(i == index - 1){
                Node deleteNode = temp.next;
                temp.next = temp.next.next;
                return deleteNode;
            }
            i++;
            temp = temp.next;
        }
        System.gc();//调用GC回收垃圾
        return null;
    }

    /**
     * 查询指定位置节点的数值
     * 从头节点开始下标为0
     * @param index
     * @return
     */
    public Object searchNode(Integer index){
        Integer i = 0;
        Node temp = this.head;
        if(size <= 1){//链表长度为1,即只有头节点
            System.out.println("链表为空!");
            return null;
        }
        if(index < 1 || index > size - 1){//非头节点的范围是1 <= index <= size-1
            System.out.println("查询位置不合法");
            return null;
        }
        while(temp.next != null){
            if(i == index){//查询到该节点
                return temp.data;
            }
            i++;
            temp = temp.next;
        }
        return null;
    }

    /**
     * 修改指定位置节点的数值
     * @param data
     * @param index
     * @return
     */
    public Object modifyNode(Object data, Integer index){
        Integer i = 0;
        Node temp = this.head;
        if(size <= 1){//链表长度为1,即只有头节点
            System.out.println("链表为空!");
            return null;
        }
        if(index < 1 || index > size-1){//非头节点的范围是1 <= index <= size-1
            System.out.println("查询位置不存在");
            return null;
        }
        while(temp.next != null){
            if(i == index){
                Object d = temp.data;
                temp.data = data;
                return d;
            }
            i++;
            temp = temp.next;
        }
        return null;
    }

    /**
     * 打印链表
     */
    public void show(){
        Node temp = null;
        temp = head;
        System.out.println("[" + temp.data + "->" + temp.next + "]");
        while(temp.next != null){
            temp = temp.next;
            System.out.println("[" + temp.data + "->" + temp.next + "=" + temp + "]");
        }
    }
}
