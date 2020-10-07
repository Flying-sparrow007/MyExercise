package day02;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();

        //单链表
        //test.useSingleLink();

        //循环链表
        //test.useCircleLink();

        //双向链表
        //test.useDoublyLink();

        //双向循环链表
        //test.useDoublyCircleLink();
    }

    /**
     * 使用单链表
     */
    private void useSingleLink(){
        SingleLink s = new SingleLink();

        //头插法
        /*s.insertTail("第一个节点");
        s.insertTail("第二个节点");
        s.insertTail("第三个节点");*/

        //尾插法
        /*s.insertHead("第一个节点");
        s.insertHead("第二个节点");
        s.insertHead("第三个节点");*/

        //删除指定位置的节点
        s.deleteNode(3);

        //在指定位置插入节点
        //s.insertNode("第四个节点", 4);

        //查询指定位置节点的内容
        //System.out.println(s.searchNode(3));

        //修改指定位置节点的数值
        //s.modifyNode("第四个节点", 3);

        s.show();
    }

    /**
     * 使用循环链表
     */
    private void useCircleLink(){
        CircleLink c = new CircleLink();

        //尾插法
        /*c.insertTail("第一个节点");
        c.insertTail("第二个节点");
        c.insertTail("第三个节点");*/

        //头插法
        /*c.insertHead("第一个节点");
        c.insertHead("第二个节点");
        c.insertHead("第三个节点");*/

        //删除指定位置的节点
        c.deleteNode(3);

        //在指定位置插入节点
        //c.insertNode("第四个节点", 4);

        //查寻指定位置节点的内容
        //System.out.println(c.searchNode(4));

        //修改指定位置的节点内容
        //c.modifyNode("第四个节点", 2);

        c.show();
    }

    /**
     * 使用双向链表
     */
    private void useDoublyLink(){
        DoublyLink d = new DoublyLink();

        //尾插法
        d.insertTail("第一个节点");
        d.insertTail("第二个节点");
        d.insertTail("第三个节点");

        //头插法
        /*d.insertHead("第一个节点");
        d.insertHead("第二个节点");
        d.insertHead("第三个节点");*/

        //在指定位置插入节点
        //d.insertNode("第四个节点", 4);

        //删除指定位置的节点
        //d.deleteNode(1);

        //查询指定位置的节点内容
        //System.out.println(d.searchNode(1));

        //修改指定位置的节点
        //d.modifyNode("第四个节点", 1);

        //输出双向链表
        d.show();
    }

    /**
     * 使用双向循环链表
     */
    private void useDoublyCircleLink(){
        DoublyCircleLink dc = new DoublyCircleLink();

        //头插法
        /*dc.insertHead("第一个节点");
        dc.insertHead("第二个节点");
        dc.insertHead("第三个节点");*/

        //尾插法
        dc.insertTail("第一个节点");
        dc.insertTail("第二个节点");
        dc.insertTail("第三个节点");

        //在指定位置插入节点
        //dc.insertNode("第四个节点", 1);

        //删除指定位置的节点
        //dc.deleteNode(3);

        //修改指定位置的节点的内容
        //dc.modifyNode("第四个节点", 3);

        //查询指定位置的节点的内容
        //System.out.println(dc.searchNode(1));

        //打印双向循环链表
        dc.show();
    }

}
