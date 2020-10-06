package day02;

/**
 * 双向链表
 * 不带头节点
 */
public class DoublyLink {
    private Node head;//头节点地址
    private Integer length;//链表长度

    public DoublyLink(){
        this.head = null;
        this.length = 0;
    }

    class Node{
        Object data;//链表数据
        Node pre;//链表的直接前驱
        Node end;//链表的直接后继

        public Node(Object data){
            this.data = data;
            this.pre = null;
            this.end = null;
        }
    }

    /**
     * 尾插法
     * @param val
     * @return
     */
    public boolean insertTail(Object val){
        //创建节点
        Node node = new Node(val);
        Node temp = this.head;
        if(temp == null){//判断链表是否为空
            head = node;
            temp = head;
            temp.end = null;
            temp.pre = null;
            length++;
            return true;
        }else{
            while(temp.end != null){
                temp = temp.end;
            }
            node.end = temp.end;
            node.pre = temp;
            temp.end = node;
            length++;
            return true;
        }
    }

    /**
     * 头插法
     * @param val
     * @return
     */
    public boolean insertHead(Object val){
        Node node = new Node(val);
        Node temp = this.head;
        if(temp == null){//判断链表是否为空
            head = node;
            temp = head;
            temp.end = null;
            temp.pre = null;
            length++;
            return true;
        }else{
            node.pre = temp.pre;
            temp.pre = node;
            node.end = temp;
            /*
             * 没有头节点,进行头插法之后头节点变成node了
             * 因此需要修改head指向的地址
             */
            head = node;
            length++;
            return true;
        }
    }

    /**
     * 在指定位置插入节点
     * @param val
     * @param index
     * @return
     */
    public boolean insertNode(Object val, Integer index){
        Node node = new Node(val);
        Node temp = head;
        Integer i = 1;
        if(index < 1 || index > length + 1){
            System.out.println("插入位置不合法,请重新插入!");
            return false;
        }
        //在第一个节点的前面插入
        if(index == 1){
            insertHead(val);
            return true;
        }
        //在最后一个节点的后面插入
        if(index == length + 1){
            insertTail(val);
            return true;
        }
        //在中间任意位置插入
        while (i <= length){
            if(i == index){
                //先把新节点连接到链表上
                node.pre = temp.pre;
                node.end = temp;
                //新节点已连接上,断开旧连接
                temp.pre.end = node;
                temp.pre = node;
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
        Integer i = 1;
        Node temp = this.head;
        if(length < 1){
            System.out.println("链表为空,无法删除!");
            return null;
        }
        if(index < 1 || index > length){
            System.out.println("删除位置不合法,请重新输入!");
            return null;
        }
        //删除位置为第一个节点
        if(index == 1){
            //头节点后移,即head指向第二个节点
            head = temp.end;
            temp.end.pre = null;
            temp.end = null;
            length--;
            return temp.data;
        }
        //删除位置为最后一个节点
        if(index == length){
            while (temp.end != null){
                temp = temp.end;
            }
            temp.pre.end = null;
            temp.pre = null;
            length--;
            return temp.data;
        }
        //在链表的中间指定位置删除
        while (i <= length){
            if(i == index){
                //将当前节点的上一个节点和下一个节点连接起来
                temp.pre.end = temp.end;
                temp.end.pre = temp.pre;
                //断开当前节点的与链表的连接
                temp.pre = null;
                temp.end = null;
                length--;
                return temp.data;
            }
            i++;
            temp = temp.end;
        }
        return null;
    }

    /**
     * 查询指定位置的节点的内容
     * @param index
     * @return
     */
    public Object searchNode(Integer index){
        Integer i = 1;
        Node temp = this.head;
        if(length < 1){//链表为空
            System.out.println("链表为空,查询失败!");
            return null;
        }
        if(index < 1 || index > length){
            System.out.println("查询位置有误,请重新输入!");
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
     * 修改指定位置的节点的内容
     * @param val
     * @param index
     * @return
     */
    public Object modifyNode(Object val, Integer index){
        Integer i = 1;
        Node temp = this.head;
        if(length < 1){
            System.out.println("链表为空,修改失败!");
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

    /**
     * 输出双向链表
     */
    public void show(){
        Node temp = this.head;
        if(length < 1){
            System.out.println("链表为空!");
            return ;
        }
        System.out.println("内容:" + temp.data
                            + ",前驱:"+ temp.pre
                            + ",后继:" + temp.end
                            + ",自身:" + temp);
        while (temp.end != null){
            temp = temp.end;
            System.out.println("内容:" + temp.data
                            + ",前驱:"+ temp.pre
                            + ",后继:" + temp.end
                            + ",自身:" + temp);
        }
    }

}
