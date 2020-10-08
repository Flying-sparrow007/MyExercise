package day03;

/**
 * 测试栈和队列
 */
public class TestStack {
    public static void main(String[] args) {
        TestStack t = new TestStack();

        //顺序栈
        //t.useOrderStack();

        //双端顺序栈
        //t.useDoublyOrderStack();

        //链栈
        //t.useLinkedStack();

        //多栈
        t.useMoreStack();
    }

    /**
     * 使用顺序栈
     */
    public void useOrderStack(){
        OrderStack os = new OrderStack();

        //压栈
        os.push("一");
        os.push("二");
        os.push("三");
        os.push("四");
        os.push("五");
        os.push("六");
        os.push("七");
        os.push("八");
        os.push("九");
        os.push("十");

        //出栈
        //System.out.println(os.pop());

        //获取栈顶元素
        System.out.println(os.peek());

        //获取栈的长度
        System.out.println(os.length());

        //查找元素
        System.out.println(os.search("十"));

        //打印栈
        os.show();
    }

    /**
     * 使用双端顺序栈
     */
    private void useDoublyOrderStack(){
        DoublyOrderStack dos = new DoublyOrderStack();

        //1号栈入栈
        /*dos.pushStack1("1");
        dos.pushStack1("2");
        dos.pushStack1("3");
        dos.pushStack1("4");
        dos.pushStack1("5");*/

        //2号栈入栈
        /*dos.pushStack2("一");
        dos.pushStack2("二");
        dos.pushStack2("三");
        dos.pushStack2("四");
        dos.pushStack2("五");*/

        //1号栈出栈
        //System.out.println(dos.popStack1());

        //2号栈出栈
        //System.out.println(dos.popStack2());

        //1号栈栈顶元素
        //System.out.println(dos.peekStack1());

        //2号栈栈顶元素
        //System.out.println(dos.peekStack2());

        //1号和2号栈的长度
        System.out.println("1号栈长度: " + dos.lengthStack1() + ", 2号栈长度: " + dos.lengthStack2());

        //判断1号栈和2号栈是否为空
        System.out.println("1号栈是否为空: " + dos.isEmptyStack1() + ", 2号栈是否为空: " + dos.isEmptyStack2());

        //查询1号栈和2号栈的元素
        System.out.println("1号栈: " + dos.searchStack1("1"));
        System.out.println("2号栈: " + dos.searchStack2("七"));

        //打印栈
        dos.show();
    }

    /**
     * 使用链栈
     */
    private void useLinkedStack(){
        LinkedStack ls = new LinkedStack();

        //入栈
        ls.push("1");
        ls.push("2");
        ls.push("3");
        ls.push("4");
        ls.push("5");

        //出栈
        /*System.out.println(ls.pop());
        System.out.println(ls.pop());
        System.out.println(ls.pop());
        System.out.println(ls.pop());
        System.out.println(ls.pop());
        System.out.println(ls.pop());*/

        //获取栈顶元素
        /*System.out.println(ls.peek());
        System.out.println(ls.peek());*/

        //查找指定元素的下标
        //System.out.println(ls.search("8"));

        //获取链表长度
        System.out.println(ls.length());

        //打印栈
        ls.show();
    }

    /**
     * 使用多栈
     */
    public void useMoreStack(){
        MoreStack ms = new MoreStack();

        //入栈
        ms.push("第一个节点", 1);//第一个链栈
        ms.push("第one个节点", 5);//第五个链栈
        ms.push("第first个节点", 10);//第十个链栈
        //ms.push("第一个节点", 11);//第十一个链栈

        //出栈
        /*System.out.println(ms.pop(1));//第一个链栈
        System.out.println(ms.pop(3));//第三个链栈
        System.out.println(ms.pop(11));//第十一个链栈*/

        //获取栈顶元素
        /*System.out.println(ms.peek(1));//第一个链栈
        System.out.println(ms.peek(3));//第三个链栈
        System.out.println(ms.peek(11));//第十一个链栈*/

        //查找栈元素位置
        /*System.out.println(ms.search("第一个节点", 1));
        System.out.println(ms.search("第一个节点", 5));*/

        //获取栈的长度
        /*System.out.println(ms.length(1));
        System.out.println(ms.length(2));
        System.out.println(ms.length(11));*/

        //判断栈是否为空
        System.out.println(ms.isEmpty(1));
        System.out.println(ms.isEmpty(2));

        //打印多栈
        //ms.show();
    }
}
