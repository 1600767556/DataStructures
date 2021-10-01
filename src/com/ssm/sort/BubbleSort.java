package com.ssm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/1 18:12
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 11, -5,};
        //System.out.println("排序前" + Arrays.toString(arr));
        //测试冒泡排序的速度 O(n^2) ,给80000个数据测试
        int[] arr = new int[800];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间是:"+simpleDateFormat.format(date));

        bubbleSort(arr);
        //System.out.println("排序后" + Arrays.toString(arr));
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序后的时间是:"+simpleDateFormat2.format(date2));
       /*
        //第二趟排序,就是将倒数第二大的排到倒数第二位
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组" + Arrays.toString(arr));

        //第三趟排序,就是将倒数第三大的排到倒数第三位
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组" + Arrays.toString(arr));

        //第四趟排序,就是将倒数第四大的排到倒数第四位
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组" + Arrays.toString(arr));
        */
    }
    //冒泡排序方法
    public static void bubbleSort(int[] arr) {
        //冒泡排序时间复杂度: O(n^2)
        int temp = 0;
        boolean flag = false; //标识符 表示是否发生过交换
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            if (!flag) { // 在一次排序中没有发生过交换
                break;
            } else {
                flag = false; // 重置flag!!!,进行下次判断
            }
        }
    }
}
