package day02;

/**
 * 带头节点的单链表
 */
public class SingleLink {
    /**链表类中持有节点的引用*/
    private Node head;
    private Integer size;//链表长度

    /**
     * 创建单链表
     */
    public SingleLink(){
        head = new Node();
        initializeHeadNode();
    }

    /**
     * 初始化头节点
     * 在这里重新初始化头节点的原因是:
     * 在Node类中初始化head还未存在,为了保证代码的健壮性,这里再次初始化,从而确保数据的准确性
     */
    private void initializeHeadNode(){
        this.head.data = "data";
        this.head.next = null;
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
    public boolean insertTail(Object val){
        Node newNode = new Node(val);
        Node temp = null;
        temp = this.head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = null;
        size++;
        return true;
    }

    /**
     * 头插法
     * @param val
     * @return
     */
    public boolean insertHead(Object val){
        Node newNode = new Node(val);
        Node temp = null;
        temp = this.head;
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
        return true;
    }


    /**
     * 在指定位置插入节点
     * @param val
     * @param index
     * @return
     */
    public boolean insertNode(Object val, Integer index){
        Node newNode = new Node(val);
        Integer i = 0;//遍历节点的下标
        Node temp = this.head;
        if(index < 1 || index > size){//插入位置小于头节点的位置或超过最后一个节点的下一个的下一个节点
            System.out.println("插入位置不合法");
            return false;
        }
        //在链表的第一个非头节点之前插入
        if(index == 1){
            insertHead(val);
            return true;
        }
        //在链表最后一个非头节点之后插入
        if(index == size){
            insertTail(val);
            return true;
        }
        //在链表中间的任意位置插入
        while(i < size - 1){
            //寻找所要插入位置的前驱节点
            if(i == index - 1){
                newNode.next = temp.next;
                temp.next = newNode;
                size++;
                return true;
            }
            i++;
            temp = temp.next;
        }
        return false;
    }

    /**
     * 删除指定位置的节点
     * @param index
     * @return
     */
    public Object deleteNode(Integer index){
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
        //这段代码和CircleLink中的相应位置的代码重复,所以报黄
        while(i < size - 1){//不使用temp.next != null的原因是,删除的位置有可能是最后一个节点,它的next为null
            //寻找被删节点的前驱节点
            if(i == index - 1){
                Node deleteNode = temp.next;
                Node tempNext = temp.next;
                temp.next = tempNext.next;
                tempNext.next = null;//释放被删除的节点
                size--;
                return deleteNode.data;
            }
            i++;
            temp = temp.next;
        }
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
        while(i < size){
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
        while(i < size){
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
