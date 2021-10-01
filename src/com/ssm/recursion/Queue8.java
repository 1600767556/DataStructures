package com.ssm.recursion;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/30 20:43
 */
public class Queue8 {
    //定义最大皇后数
    int max = 8;
    //定义数组arr,保存皇后放置位置的结果 arr={0,4,7,5,2,6,1,3}
    int [] array = new int [max];
    static int count =0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d中解法\n",count);
        System.out.printf("一共判断了%d次冲突",judgeCount);

    }
    //放置第n个皇后
    //特别注意 : check 是 每一次递归时,进入到check中都有for (int i = 0; i <max ; i++),因此会有回溯
    private void  check(int n){
        if (n == max) { // n = 8 8个皇后已经放好
            print();
            return;
        }
        //依次放入皇后 并判断是否冲突
        for (int i = 0; i <max ; i++) {
            //先把当前这个皇后n,放到改行的第一列
            array[n] = i;
            //判断当前第n个皇后到i列时,是否冲突
            if (judge(n)){ //不冲突
                check(n+1);
            }
            //如果冲突 继续执行 array[n] = i;即将第n个皇后,放置在本行的后移一个位置
        }
    }

    //查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
    public boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 1.array[i] == array[n]  判断第n个皇后是否和前面的n-1个皇后是否在同一列
            // 2.Math.abs(n-i) == Math.abs(array[n] - array[i]  判断第n个皇后是否和前面的n-1个皇后是否在同一斜线
            // 3. 判断是否在同一行 没必要 每次都递增
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
    //将皇后摆放位置输出
    private void print(){
        count++;
        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
