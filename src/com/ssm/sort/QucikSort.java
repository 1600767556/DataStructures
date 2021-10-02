package com.ssm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/2 20:22
 */
public class QucikSort {
    public static void main(String[] args) {
       // int[] arr = {-9, 78, 0, 23, -56, 70,45,12,48,13};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间是:"+simpleDateFormat.format(date));

        qucikSort(arr, 0, arr.length - 1);
        System.out.println("排序后" + Arrays.toString(arr));
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序后的时间是:"+simpleDateFormat2.format(date2));

        qucikSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void qucikSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];//中轴值
        int temp = 0; //临时变量作为交换使用
        //while 循环的目的是让比pivot值 小的放左边, 大的放右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot的值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot的值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果 l>=r 说明pivot的左右两边的值已经按照左边全是小于等于pivot,右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后发现arr[l] == pivot 值相等r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后发现arr[r] == pivot 值相等l++,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //必须l++,r--,否则栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left<r){ // 这里的r已发生变化 r -= 1;
            qucikSort(arr,left,r);
        }
        //向右递归
        if (right>l){ //这里的l已发生变化  l += 1;
            qucikSort(arr,l,right);
        }
    }
}
