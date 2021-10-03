package com.ssm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/3 15:34
 */
public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {53,3,542,748,14,214};


        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间是:"+simpleDateFormat.format(date));

        radixSort(arr);
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序后的时间是:"+simpleDateFormat2.format(date2));

    }

    public static void radixSort(int[] arr) {
        //得到数组中最大数的位数
        int max = arr[0];//假设第一个数就是最大的
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; //得到最大数
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();
        //定义一个二维数组,表示10个桶子,每个桶就是一个一维数组
        //共10个一维数组,为防止栈溢出,则每个一维数组大小定义为arr.length
        int[][] bucket = new int[10][arr.length];
        //定义一个一维数组用来记录每个桶中实际存放了多少个数据
        int[] bucketElementsCounts = new int[10];
        //使用循环处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应的位数进行排序处理 个位,十位,百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementsCounts[digitOfElement]] = arr[j];
                bucketElementsCounts[digitOfElement]++;
            }
            //按照桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
            int index = 0;
            for (int k = 0; k <bucketElementsCounts.length ; k++) {
                //如果桶中有数据,则放入
                if (bucketElementsCounts[k]!=0){
                    //循环该桶即第k个桶(第k个一维数组)
                    for (int l = 0; l <bucketElementsCounts[k] ; l++) {
                        //取出元素放入arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理完后需将每个bucketElementsCounts[k] = 0 !!!
                bucketElementsCounts[k] = 0;
            }
            //System.out.println("第"+(i+1)+"轮后 arr="+ Arrays.toString(arr));
        }

    }
}
