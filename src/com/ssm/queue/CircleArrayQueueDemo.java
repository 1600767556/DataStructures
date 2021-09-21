package com.ssm.queue;
import java.util.Scanner;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/15 22:22
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建队列
        CircleArray queue = new CircleArray(4);//这里设置的4其队列的有效数据最大是3
        //接受用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加程序到队列");
            System.out.println("g(get): 从队列中获取数据");
            System.out.println("h(head): 查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数 ");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    private int maxSize; //数组的最大容量
    private int front; //front指向队列的第一个元素,front 的初始值是0;
    private int rear; //rear指向队列的最后一个元素的后一个位置,因为希望空出一个空间作为约定(判断队列是否满) rear的初始值是0;
    private int[] arr; //该数组用于存放数据,模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列中
    public void addQueue(int n) {
        //判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将 rear 后移,这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列中的数据,出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据!");
        }
        /**
         * 这里需分析出front是指向队列的第一个元素
         * 1.先把front 对应的值保留到一个临时变量
         * 2.将front 后移,考虑取模
         * 3.将临时变量返回
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列中的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据~~~");
            return;
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没有数据~~");
        }
        return arr[front];
    }

}
