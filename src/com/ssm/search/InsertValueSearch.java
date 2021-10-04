package com.ssm.search;

import java.util.Arrays;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/4 14:18
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println("index = "+insertValueSearch(arr,0,arr.length-1,1));

        //System.out.println(Arrays.toString(arr));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //注意findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须有 否则可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findVal > midValue) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
