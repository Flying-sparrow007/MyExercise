package day02;

/**
 * 双向循环链表
 * 不含头节点
 */
public class DoublyCircleLink {
    private Node head;//存储链表的头部
    private Integer length;//存储链表的长度

    public DoublyCircleLink(){
        this.head = null;
        this.length = 0;
    }

    /**
     * 节点内部类
     */
    class Node{
        private Object data;
        private Node pre;
        private Node end;

        public Node(Object val){
            this.data = val;
            this.end = null;
            this.pre = null;
        }
    }

    /**
     * 头插法
     * @param val
     * @return
     */
    public boolean insertHead(Object val){
        Node newNode = new Node(val);
        Node temp = this.head;
        if(temp == null){//判断链表是否为空
            head = newNode;
            temp = head;
            temp.end = head;
            temp.pre = head;
            length++;
            return true;
        }else{
            //将新节点连接到链表上
            newNode.pre = temp.pre;
            newNode.end = temp;
            //断开旧的连接,重新连接到新节点上
            temp.pre.end = newNode;
            temp.pre = newNode;
            //挪动head指向新的头节点
            head = newNode;
            length++;
            return true;
        }
    }

    /**
     * 尾插法
     * @param val
     * @return
     */
    public boolean insertTail(Object val){
        Node newNode = new Node(val);
        Node temp = this.head;
        if(temp == null){
            head = newNode;
            temp = head;
            temp.end = head;
            temp.pre = head;
            length++;
            return true;
        }else{
            while (temp.end != head){
                temp = temp.end;
            }
            //将新节点连接到链表上
            newNode.pre = temp;
            newNode.end = temp.end;
            //断开旧连接,重新连接到新节点
            temp.end.pre = newNode;
            temp.end = newNode;
            length++;
            return true;
        }
    }

    /**
     * 在链表的指定位置插入节点
     * @param val
     * @param index
     * @return
     */
    public boolean insertNode(Object val, Integer index){
        Node newNode = new Node(val);
        Integer i = 1;
        Node temp = this.head;
        if(index < 1 || index > length + 1){//1 <= 节点下标 <= length,但可以在最后一个节点的后面插入
            System.out.println("插入位置有误,请重新输入!");
            return false;
        }
        //在第一个节点之前插入
        if(index == 1){
            insertHead(val);
            return true;
        }
        //在最后一个节点之后插入
        if(index == length + 1){
            insertTail(val);
            return true;
        }
        //在中间任意位置插入
        while (temp.end != head){
            if(i == index){
                //将新节点连接到链表上
                newNode.pre = temp.pre;
                newNode.end = temp;
                //断开旧连接,重新连接到新节点
                temp.pre.end = newNode;
                temp.pre = newNode;
                length++;
                return true;
            }
            i++;
            temp = temp.end;
        }
        return false;
    }

    /**
     * 删除指定位置的节点
     * @param index
     * @return
     */
    public Object deleteNode(Integer index){
        Node temp = this.head;
        Integer i = 1;
        if(length < 1){
            System.out.println("链表为空无法删除");
            return null;
        }
        if(index < 1 || index > length){
            System.out.println("删除位置有误,无法删除!");
            return null;
        }
        while (i <= length){
            if(i == index){
                if(index == 1){
                    /*
                     * 如果删除的节点为第一个节点,即index == 1, 需多一个步骤
                     * 先将head后移,即head = temp.end;在进行节点删除,
                     * 从而保证链表正常调用
                     */
                    head = temp.end;
                }
                //重新改变链表结构,使得被删除节点没有变量引用
                temp.pre.end = temp.end;
                temp.end.pre = temp.pre;
                //将被删除节点从链表中断开.
                temp.pre = null;
                temp.end = null;
                length--;//链表长度减一
                return temp.data;
            }
            i++;
            temp = temp.end;
        }
        return null;
    }

    /**
     * 修改指定位置的节点的内容
     * @param index
     * @return
     */
    public Object modifyNode(Object val, Integer index){
        Integer i = 1;
        Node temp = this.head;
        if(length < 1){
            System.out.println("链表为空,无法修改!");
            return null;
        }
        if(index < 1 || index > length){
            System.out.println("修改位置有误,请重新输入!");
            return null;
        }
        while (i <= length){
            if(i == index){
                Object data = temp.data;
                temp.data = val;
                return data;
            }
            i++;
            temp = temp.end;
        }
        return null;
    }

    public Object searchNode(Integer index){
        Integer i = 1;
        Node temp = this.head;
        if(length < 1){
            System.out.println("链表长度为空,无法查询!");
            return null;
        }
        if(index < 1 || index > length){
            System.out.println("查询位置不对,请重新输入!");
            return null;
        }
        while (i <= length){
            if(i == index){
                return temp.data;
            }
            i++;
            temp = temp.end;
        }
        return null;
    }

    /**
     * 打印链表
     */
    public void show(){
        Node temp = this.head;
        if(temp == null){//链表为空
            System.out.println("链表为空");
            return ;
        }
        System.out.println("内容:" + temp.data
                        + ", 前驱:" + temp.pre
                        + ", 后继:" + temp.end
                        + ", 自身:" + temp
                            );
        while (temp.end != head){
            temp = temp.end;
            System.out.println("内容:" + temp.data
                            + ", 前驱:" + temp.pre
                            + ", 后继:" + temp.end
                            + ", 自身:" + temp
                                );
        }
    }
}
