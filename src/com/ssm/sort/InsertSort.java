package com.ssm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/2 13:01
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 2};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间是:" + simpleDateFormat.format(date));

        insertSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序后的时间是:" + simpleDateFormat2.format(date2));


    }

    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;//即arr[1]前面这个数的下标
        // 第一轮{101,34,119,2}; => {34,101,119,2};
        // 定义待插入的数
        for (int i = 1; i < arr.length; i++) {


            insertVal = arr[i];
            insertIndex = i - 1;//即arr[1]前面这个数的下标

            //给insert找到插入的位置
            // 1.insertIndex >= 0 保证在给insertVal 找到插入位置,不越界
            // 2.insertVal < arr[insertIndex] 待插入的数,还没有找到插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时,说明插入位置找到,insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            // System.out.println("第"+i+"轮插入" + Arrays.toString(arr));
        }
    }

}
