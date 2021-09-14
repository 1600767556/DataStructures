package com.ssm.sparsearray;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/14 18:30
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个二位数组 11*11
        //0: 表示没有去棋子  1:表示黑子  2: 表示蓝子
        int [][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[0][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //二维数组 ------>  稀疏数组
        //1.遍历原始的二维数组得到sum总数
        int sum = 0;
        for (int i = 0; i <chessArr1.length ; i++) {
            for (int j = 0; j <chessArr1.length ; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArr2 = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr2[0][0] = chessArr1.length;
        sparseArr2[0][1] = chessArr1.length;
        sparseArr2[0][2] = sum;

        //遍历二维数组 将非0的值存放到sparseArr中
        // 用于记录第几个非0数据
        int  count = 0;
        for (int i = 0; i <chessArr1.length ; i++) {
            for (int j = 0; j <chessArr1.length ; j++) {

                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr2[count][0] = i;
                    sparseArr2[count][1] = j;
                    sparseArr2[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();

        System.out.println("得到的稀疏数组为~~~~");
        for (int i = 0; i <sparseArr2.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);
        }
        System.out.println();

        /**
         * 稀疏数组 ---->二维数组
         */

        //1.先读取稀疏数组的第一行,根据第一行数据,创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //2.再读取稀疏数组从第二行开始的数据,并赋值给二维数组即可
        for (int i = 1; i <sparseArr2.length ; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        System.out.println("恢复后的二维数组~~~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



    }

}
