package com.ssm.sort;

import java.util.Arrays;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/9 16:55
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }


    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {

        int temp = 0;
        //最终代码
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /**
         * 将其与末尾元素进行交换，此时末尾就为最大值。
         * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println("数组: " + Arrays.toString(arr)); //9, 6, 8, 5, 4

    }

    //将一个数组(二叉树),调整成一个大顶堆

    /**
     * 完成将以i 对应的非叶子结点的树调整成大顶堆
     *
     * @param arr    待调整数组
     * @param i      非叶子结点在数组中索引
     * @param length 表示多少个元素要继续调整 length在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i]; //先去除当前元素的值,保存在临时变量
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子结点的值小于右子结点的值
                k++; //k 指向右子结点
            }
            if (arr[k] > temp) { //子节点大于父结点
                arr[i] = arr[k]; //把较大的值赋值给当前结点
                i = k;  //!!! i指向k,继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束时,我们已经将以i为父结点的树的最大值,放在了最顶部(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }


}
