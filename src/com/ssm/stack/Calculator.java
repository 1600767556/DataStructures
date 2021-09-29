package com.ssm.stack;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/25 22:09
 */
public class Calculator {
    public static void main(String[] args) {
        String experssion = "70+2*6-4";
        //创建两个栈 数栈,符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接多位数
        while (true) {
            ch = experssion.substring(index, index + 1).charAt(0);
            //判断ch是什么,然后做相应的处理
            if (operStack.isOper(ch)) { //如果是运算符
                //判断当前栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈中有操作符,就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号,进行运算,将得到的结果入数栈,然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将得到的结果入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级小于或者等于栈中的操作符,就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }

            } else { //如果是数,则直接入数栈
                //1.当处理多位数时,不能发现是一个数就直接入栈
                //2.在处理数时,需要向expression的表达式的index 后再看一位,如果是数就扫描,如果是符号才入栈
                //3. 定义一个字符串变量用于拼接
                keepNum+=ch;
                //如果expression是最后一位,就直接入栈
                if (index == experssion.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字,,如果是数就扫描,如果是符号才入栈
                    if (operStack.isOper(experssion.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符,则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空!!!!!
                        keepNum="";
                    }
                }

            }
            index++;
            if (index >=experssion.length()){
                break;
            }
        }
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("表达式%s = %d",experssion,numStack.pop()) ;
    }
}

class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟战
    private int top = -1; //top表示栈顶,初始值为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 返回当前栈顶的值
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级,优先级用数字表示
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0; //res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }


}
 