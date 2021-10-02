package com.ssm.sort;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/1 22:18
 */
public class SelectSort {
    public static void main(String[] args) {
        //int [] arr = {101,34,119,1};

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间是:"+simpleDateFormat.format(date));

        selectSort(arr);
        //System.out.println("排序后" + Arrays.toString(arr));
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序后的时间是:"+simpleDateFormat2.format(date2));


        //System.out.println("排序前:"+ Arrays.toString(arr));
        //selectSort(arr);
        //System.out.println("排序后:"+ Arrays.toString(arr));


    }
    //选择排序时间复杂度O(n^2)
    public static void selectSort(int[] arr) {
        for (int i = 0; i <arr.length ; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //说明假定的最小值不是最小值
                    min = arr[j]; //重置min
                    minIndex = j; // 重置minindex
                }
            }
            //将最小值,放在arr[0],即交换
            arr[minIndex] = arr[i];
            arr[i] = min;
           // System.out.println("第"+(i+1)+"轮后:" + Arrays.toString(arr));

        }

    }

}
