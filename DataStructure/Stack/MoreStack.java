package day03;

/**
 * 多栈
 */
public class MoreStack {
    private LinkedStack[] head;//数组,存储链栈的头地址
    private Integer size;//链栈的个数
    private static final Integer MAX_LENGTH = 10;//链栈最大个数

    /**
     * 初始化存储链栈头地址的数组
     */
    public MoreStack(){
        head = new LinkedStack[MAX_LENGTH];
        size = 0;
        initializeHeadArrays();
    }

    /**
     * 初始化存储链栈的头地址
     */
    private synchronized void initializeHeadArrays(){
        LinkedStack[] temp = this.head;
        for(int i = 0; i < MAX_LENGTH; i++){
            temp[i] = new LinkedStack();
        }
    }

    /**
     * 入栈
     * @param val 入栈的元素
     * @param index  选择入栈的链表
     * @return
     */
    public synchronized boolean push(Object val, Integer index){
        LinkedStack[] temp = this.head;
        if(index > MAX_LENGTH){
            System.out.println(index + " 栈不存在,入栈失败!");
            return false;
        }
        temp[index - 1].push(val);
        return true;
    }

    /**
     * 出栈
     * @param index
     * @return
     */
    public synchronized Object pop(Integer index){
        LinkedStack[] temp = this.head;
        if(index > MAX_LENGTH){
            System.out.println(index + " 栈不存在,出栈失败!");
            return null;
        }
        Object data = temp[index - 1].pop();
        return data;
    }

    /**
     * 获取栈顶元素
     * @param index
     * @return
     */
    public Object peek(Integer index){
        LinkedStack[] temp = this.head;
        if(index > MAX_LENGTH){
            System.out.println(index + " 栈不存在,获取元素失败!");
            return -1;
        }
        Object data = temp[index - 1].peek();
        return data;
    }

    /**
     * 获取栈中指定元素的下标
     * @param val
     * @param index
     * @return
     */
    public Integer search(Object val, Integer index){
        LinkedStack[] temp = this.head;
        if(index > MAX_LENGTH){
            System.out.println(index + " 栈不存在,获取失败!");
            return -1;
        }
        Integer i = temp[index - 1].search(val);
        return i;
    }

    /**
     * 获取栈的长度
     * @param index
     * @return
     */
    public Integer length(Integer index){
        LinkedStack[] temp = this.head;
        if(index > MAX_LENGTH){
            System.out.println(index + " 栈不存在!");
            return -1;
        }
        Integer i = temp[index - 1].length();
        return i;
    }

    /**
     * 判断栈是否为空
     * @param index
     * @return
     */
    public boolean isEmpty(Integer index){
        LinkedStack[] temp = this.head;
        if(index > MAX_LENGTH){
            System.out.println(index + " 栈不存在!");
            return false;
        }
        boolean flag = temp[index - 1].isEmpty();
        return flag;
    }

    /**
     * 打印多栈
     */
    public synchronized void show(){
        LinkedStack[] temp = this.head;
        for(int i = 0; i < MAX_LENGTH; i++){
            System.out.println("--------------");
            System.out.println((i + 1) + "号栈: ");
            if(temp[i] == null){
                System.out.println("栈为空!");
                continue;
            }
            temp[i].show();
        }
    }

}
