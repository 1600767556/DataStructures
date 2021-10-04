package com.ssm.search;

import java.util.Arrays;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/4 14:43
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 20, 89, 1000, 1234};
        System.out.println("index= " + fibSearch(arr, 89));
    }

    //因为后面mid = low+F(k-1)-1,需要使用斐波那契数列,所以先获取一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    //编写斐波那契查找算法(非递归)

    /**
     * @param a   数组
     * @param key 需要查找的关键码（值）
     * @return 返回对应的下标 没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;// 斐波那契数组下标
        int mid = 0; //存放mid值
        int[] f = fib();//获取到斐波那契数列
        //获取到斐波那契数组的下标
        while (high > f[k] - 1) {  //用high和 f[k]中的值比较(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)  > 表示还没找到
            k++;
        }
        //因为f[k] 值可能大于a的长度,因此我们需要使用Arrays类,构造一个新的数组,并指向temp[],不足的部分用0补齐
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上是用a数组最后的数填充temp eg:temp = {1, 8, 20, 89, 1000, 1234,0,0} => temp = {1, 8, 20, 89, 1000, 1234,1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //找到 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //向数组的前面查找
                high = mid - 1;
                //说明 1.全部元素 = 前面的元素+后面的元素
                //2. f[k]+f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2]+f[k-3]
                //即 f[k-1]的前面继续查找 k-- 即下次循环mid =low+ f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { // 后面查找
                low = mid + 1;
                //说明 1.全部元素 = 前面的元素+后面的元素
                //2. f[k]+f[k-1]+f[k-2]
                //因为前面有f[k-2]个元素,所以可以继续拆分 f[k-2] = f[k-3]+f[k-4]
                //即 f[k-2]的前面继续查找 k-=2 即下次循环mid =low+ f[k-1-2]-1
                k -= 2;
            } else { //找到
                //需要确定返回的是哪个下表
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
