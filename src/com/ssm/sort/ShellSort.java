package com.ssm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/2 14:27
 */
public class ShellSort {
    public static void main(String[] args) {
         int [] arr = {8,9,1,7,2,3,5,4,6,0};
        //int[] arr = new int[80000];
/*        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }*/
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间是:" + simpleDateFormat.format(date));

        shellSort2(arr);
        //shellSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序后的时间是:" + simpleDateFormat2.format(date2));

    }

    //希尔排序
    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {


            //第一轮 10个数据分成了5组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有元素(共gap组,每组中有2个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) { //j-=5 为了跳出循环
                    //如果当前元素大于加上步长后的那个元素,说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第"+(++count)+"轮后: " + Arrays.toString(arr));
        }


    }

    //希尔排序 ->移位法
    public static void shellSort2(int[] arr) {

        //增量gap 逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j]<arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    //当退出while后,就给temp找到插入的数据
                    arr[j] = temp;
                }
            }
            System.out.println("希尔排序后: " + Arrays.toString(arr));
        }

    }
}
