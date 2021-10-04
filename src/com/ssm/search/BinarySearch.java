package com.ssm.search;

import java.util.ArrayList;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/4 13:19
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1625};
        ArrayList<Integer> i = binarySearch(arr, 0, arr.length, 1000);
        System.out.println("ArrayList="+i);
    }

    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findval) {
        //当left > right 说明没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midvalue = arr[mid];
        if (findval > midvalue) { //向右递归
            return binarySearch(arr, mid + 1, right, findval);
        } else if (findval < midvalue) {
            return binarySearch(arr, left, mid - 1, findval);
        } else {
            //1.在找到mid的索引值,不要马上返回
            //2.向mid左边扫描,将所有满足的元素的下标放入到集合ArrayList中
            //3.向mid右边扫描,将所有满足的元素的下标放入到集合ArrayList中
            //4.返回ArrayLis
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findval) {
                    break;
                } else {
                    resIndexList.add(temp);
                    temp -= 1;
                }
                resIndexList.add(mid);
                temp = mid + 1;

            }
            while (true) {
                if (temp < arr.length - 1 || arr[temp] != findval) {
                    break;
                } else {
                    resIndexList.add(temp);
                    temp += 1;
                }
            }
            return resIndexList;
        }
    }
}
