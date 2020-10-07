package day02;

/**
 * 循环链表
 */
public class CircleLink {
    private Node head;//链表
    private Integer size;//链表长度

    /**
     * 创建头节点
     */
    public CircleLink(){
        this.head = new Node();
        initializeHeadNode();
    }

    /**
     * 初始化头节点
     * 在这里重新初始化头节点的原因是:
     * 在Node类里面创建的head节点的next为null(不能写head.next = head,此时的head为null)
     * 这样会导致空链表输出时报空指针异常
     */
    private void initializeHeadNode(){
        this.head.data = "data";
        this.head.next = head;
    }

    class Node{
        Object data;//数据域
        Node next;//指针域

        /**
         * 头节点
         */
        public Node(){
            this.data = "data";
            this.next = null;
            size = 1;
        }

        /**
         * 非头节点
         */
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
        if(temp.next == null){
            temp.next = head;
        }
        while(temp.next != head){
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head;
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
        if(temp.next == null){//链表不为空
            temp.next = head;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
        return true;
    }

    /**
     * 任意位置插入节点
     * @param val
     * @param index
     * @return
     */
    public boolean insertNode(Object val, Integer index){
        Integer i = 0;
        Node newNode = new Node(val);
        Node temp = this.head;
        if(index < 1 || index > size){//下标i是从0开始的,第i = 0标识头节点,i = size - 1表示尾节点
            System.out.println("插入位置有误,请重新插入!");
            return false;
        }
        //在第一个非头节点之前插入
        if(index == 1){
            insertHead(val);
            return true;
        }
        //在第一个非头节点之后插入
        if(index == size){
            insertTail(val);
            return true;
        }
        //在中间位置任意插入
        while(i < size - 1){
            if(i == index - 1){
                newNode.next = temp.next;
                temp.next = newNode;
                return true;
            }
            i++;
            temp = temp.next;
        }
        return false;
    }

    /**
     * 删除节点
     * @param
     * @return
     */
    public Object deleteNode(Integer index){
        Integer i = 0;//头节点下标为0
        Node temp = this.head;
        if(size <= 1){//链表长度为1,即只有头节点
            System.out.println("链表为空!");
            return null;
        }
        if(index < 1 || index > size - 1){//第一个非头节点下标为1,最后一个非头节点下标为size - 1
            System.out.println("删除位置不存在!");
            return null;
        }
        //这段代码和SingleLink中的相应位置的代码重复,所以报黄
        while(i < size - 1){
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

    public Object searchNode(Integer index){
        Integer i = 0;
        Node temp = this.head;
        if(size <= 1){//链表只包含头节点
            System.out.println("链表为空,查询失败!");
            return null;
        }
        if(index < 1 || index > size - 1){//非头节点的下标范围 1 <= 非头节点 <= size - 1
            System.out.println("插入查询位置有误,请重新输入!");
            return null;
        }
        while (i < size){
            if(i == index){
                Object data = temp.data;
                return data;
            }
            i++;
            temp = temp.next;
        }
        return null;
    }

    /**
     * 修改指定位置的节点内容
     * @param index
     */
    public Object modifyNode(Object val, Integer index){
        Integer i = 0;
        Node temp = this.head;
        if(size <= 1){//链表只包含头节点
            System.out.println("链表为空,修改失败!");
            return null;
        }
        if(index < 1 || index > size - 1){// 1 <= 非头节点下标 <= size - 1
            System.out.println("下标输入有误,请重新输入!");
            return null;
        }
        while (i < size){
            if(i == index){
                Object data = temp.data;
                temp.data = val;
                return data;
            }
            i++;
            temp = temp.next;
        }
        return null;
    }

    /**
     * 输出链表
     */
    public void show(){
        Node temp = null;
        temp = head;
        System.out.println("内容:" + temp.data
                        + ", 后继:" + temp.next
                        + ", 自身:" + temp
                            );
        while(temp.next != head){
            temp = temp.next;
            System.out.println("内容:" + temp.data
                            + ", 后继:" + temp.next
                            + ", 自身:" + temp
                                );
        }
    }
}
