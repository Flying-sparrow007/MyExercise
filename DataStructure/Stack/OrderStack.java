package day03;

/**
 * 顺序栈
 */
public class OrderStack {
    private Object[] head;//栈
    private Integer size;//栈的长度
    private Integer top;//栈顶指针
    private static final Integer MAX_LENGTH = 10;

    /**
     * 初始化栈
     */
    public OrderStack(){
        this.head = new Object[MAX_LENGTH];
        this.size = 0;
        this.top = -1;
    }

    /**
     * 入栈
     * @param val
     * @return
     */
    public boolean push(Object val){
        Object[] temp = this.head;
        if(val != null){
            if(size < MAX_LENGTH){//判断栈是否已满
                //先将栈顶指针上移,然后写入数据元素,栈的长度+1
                top++;
                temp[top] = val;
                size++;
                return true;
            }else{
                System.out.println("栈已满,入栈失败!");
                return false;
            }
        }
        System.out.println("插入数据有误,数据不能为null");
        return false;
    }

    /**
     * 出栈
     * @return
     */
    public Object pop(){
        Object[] temp = this.head;
        if(size > 0){//判断栈是否为空
            //先读出栈顶元素,然后栈顶指针下移,栈的长度-1
            Object val = temp[top];
            top--;
            size--;
            return val;
        }else{
            System.out.println("栈为空,出栈失败!");
            return null;
        }
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public Object peek(){
        Object[] temp = this.head;
        if(size > 0){
            return temp[top];
        }else{
            System.out.println("栈为空,获取元素失败!");
            return null;
        }
    }

    public Integer search(Object obj){
        Object[] temp = this.head;
        if(size < 1){
            System.out.println("栈为空,查询失败!");
            return -1;
        }
        for (int i = top; i >= 0; i--){
            if(temp[i].equals(obj)){
                return (top - i + 1);
            }
        }
        return -1;
    }

    /**
     * 获取栈的长度
     * @return
     */
    public Integer length(){
        return size;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    /**
     * 打印栈
     */
    public void show(){
        Object[] temp = this.head;
        for(int i = top; i >= 0; i--){
            System.out.println((top - i + 1) + ": " + temp[i]);
        }
    }

}
