package com.ssm.tenAlgorithms;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/15 19:07
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }
    public static void hanoiTower(int num,char a,char b, char c){
        if (num==1){
            System.out.println("第1个盘从 "+a+"->"+c);
        } else {
            //n>=2 把所有的盘总是看成两个盘:1.下边的一个盘,上面的所有盘
            //1.最上面的所有盘A->B
            hanoiTower(num-1,a,c,b);
            //2.最下面的盘A->C
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            //3.把B塔的所有盘从B->C 移动过程使用到a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}
