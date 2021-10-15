package com.ssm.tenAlgorithms;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/15 18:35
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 23, 46, 413, 880, 999};
        int index = binarySearch(arr, 999);
        System.out.println(index);
    }

    /**
     * 二分查找非递归实现
     *
     * @param arr    待查找的数组 arr升序排序
     * @param target 目标数
     * @return 返回对应下标 否则返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
