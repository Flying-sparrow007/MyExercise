package day03;

/**
 * 双端顺序栈
 * 有两个栈顶指针: 栈为空时分别为 -1 MAX_LENGTH
 */
public class DoublyOrderStack {
    private Object[] head;//顺序栈
    private Integer size;//栈的大小
    private Integer[] top;//栈顶指针
    private static final Integer MAX_LENGTH = 10;

    public DoublyOrderStack(){
        this.head = new Object[MAX_LENGTH];
        this.size = 0;
        this.top = new Integer[]{-1, MAX_LENGTH};
    }

    /**
     * 1号栈入栈
     * @param val
     * @return
     */
    public synchronized boolean pushStack1(Object val){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        //判断栈是否满,即1号栈的栈顶指针+1是否等于2号栈的栈顶指针
        if((tempTop[0] + 1) == tempTop[tempTop.length - 1]){
            System.out.println("栈已满,无法入栈!");
            return false;
        }
        tempTop[0]++;
        temp[tempTop[0]] = val;
        size++;
        return true;
    }

    /**
     * 2号栈入栈
     * @param val
     * @return
     */
    public synchronized boolean pushStack2(Object val){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        //判断栈是否满,即1号栈的栈顶指针+1是否等于2号栈的栈顶指针
        if((tempTop[0] + 1) == tempTop[tempTop.length - 1]){
            System.out.println("栈已满,无法入栈!");
            return false;
        }
        tempTop[tempTop.length - 1]--;
        temp[tempTop[tempTop.length - 1]] = val;
        size++;
        return true;
    }

    /**
     * 1号栈出栈
     * @return
     */
    public synchronized Object popStack1(){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        if(isEmptyStack1()){
            System.out.println("栈为空,出栈失败!");
            return null;
        }
        //先出栈,栈顶指针在移动
        Object val = temp[tempTop[0]];
        tempTop[0]--;
        size--;
        return val;
    }

    /**
     * 2号栈出栈
     * @return
     */
    public synchronized Object popStack2(){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        if(isEmptyStack2()){
            System.out.println("栈为空,出栈失败!");
            return null;
        }
        //先出栈,栈顶指针在移动
        Object val = temp[tempTop[tempTop.length - 1]];
        tempTop[tempTop.length - 1]++;
        size--;
        return val;
    }

    /**
     * 获取1号栈栈顶元素
     * @return
     */
    public synchronized Object peekStack1(){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        if(isEmptyStack1()){
            System.out.println("栈为空!");
            return null;
        }
        Object val = temp[tempTop[0]];
        return val;
    }

    /**
     * 获取2号栈栈顶元素
     * @return
     */
    public synchronized Object peekStack2(){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        if(isEmptyStack2()){
            System.out.println("栈为空!");
            return null;
        }
        Object val = temp[tempTop[tempTop.length - 1]];
        return val;
    }

    /**
     * 1号栈的长度
     * @return
     */
    public synchronized Integer lengthStack1(){
        Integer[] tempTop = this.top;
        return tempTop[0] + 1;
    }

    /**
     * 2号栈的长度
     * @return
     */
    public synchronized Integer lengthStack2(){
        Integer[] tempTop = this.top;
        return MAX_LENGTH - tempTop[tempTop.length - 1];
    }

    /**
     * 判断1号栈是否为空
     * @return
     */
    public synchronized boolean isEmptyStack1(){
        Integer[] tempTop = this.top;
        return tempTop[0] == -1 ? true : false ;
    }

    /**
     * 判断2号栈是否为空
     * @return
     */
    public synchronized boolean isEmptyStack2(){
        Integer[] tempTop = this.top;
        return tempTop[tempTop.length - 1] == MAX_LENGTH ? true : false ;
    }

    /**
     * 查询1号栈是否有对应的val
     * 有就返回它是第几个元素
     * @param val
     * @return
     */
    public synchronized Integer searchStack1(Object val){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        if(isEmptyStack1()){
            System.out.println("栈为空,查询失败!");
            return -1;
        }
        for(Integer i = tempTop[0]; i >= 0; i--){
            if(temp[i].equals(val)){
                return tempTop[0] - i + 1;
            }
        }
        return -1;
    }

    /**
     * 查询2号栈是否有对应的val
     * 有就返回它是第几个元素
     * @param val
     * @return
     */
    public synchronized Integer searchStack2(Object val){
        Integer[] tempTop = this.top;
        Object[] temp = this.head;
        if(isEmptyStack2()){
            System.out.println("栈为空,查询失败!");
            return MAX_LENGTH;
        }
        for(Integer i = tempTop[tempTop.length - 1]; i < MAX_LENGTH; i++){
            if(temp[i].equals(val)){
                return i - tempTop[tempTop.length - 1] + 1;
            }
        }
        return MAX_LENGTH;
    }

    /**
     * 打印栈
     * 这是一个双端顺序栈,所以会输出两个栈
     */
    public void show(){
        Object[] temp = this.head;
        Integer[] tempTop = this.top;

        System.out.println("1号栈:");
        if (tempTop[0] != -1){
            for(Integer i = tempTop[0]; i >= 0; i--){
                System.out.println((tempTop[0] - i + 1) + ": " + temp[i]);
            }
        }else{
            System.out.println("1号栈为空!");
        }


        System.out.println("---------------");
        System.out.println("2号栈:");
        if(tempTop[tempTop.length - 1] != MAX_LENGTH){
            for(Integer i = tempTop[tempTop.length - 1]; i < MAX_LENGTH; i++){
                System.out.println((tempTop[tempTop.length - 1] + 1) + ": " + temp[i]);
            }
        }else {
            System.out.println("2号栈为空!");
        }

    }

}
