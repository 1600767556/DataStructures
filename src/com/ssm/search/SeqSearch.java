package com.ssm.search;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/3 16:19
 */
public class SeqSearch {
    public static void main(String[] args) {
        int [] arr = {9,45,16,8,-4,62};
        int index = seqSearch(arr,-4);
        if (index!=-1){
            System.out.println("找到了下标为:"+index);
        } else {
            System.out.println("没有找到");
        }
    }

    public static int seqSearch(int[] arr, int value) {
        //线性查找逐一对比,发现有相同值就返回下标
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }

}
